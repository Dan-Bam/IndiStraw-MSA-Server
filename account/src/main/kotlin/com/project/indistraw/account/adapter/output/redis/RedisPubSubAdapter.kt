package com.project.indistraw.account.adapter.output.redis

import com.project.indistraw.account.application.port.output.QueryQRCodeUUIDPort
import com.project.indistraw.account.application.port.output.RedisPubSubPort
import com.project.indistraw.account.application.port.output.TokenGeneratePort
import com.project.indistraw.account.application.port.output.TokenParsePort
import com.project.indistraw.account.application.port.output.dto.TokenDto
import com.project.indistraw.account.domain.Authority
import com.project.indistraw.global.security.token.common.properties.JwtProperties
import mu.KotlinLogging
import org.springframework.data.redis.connection.MessageListener
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.PatternTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter
import org.springframework.stereotype.Component
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.io.IOException
import java.time.LocalDateTime
import java.util.*
import java.util.concurrent.TimeUnit

private val log = KotlinLogging.logger {  }

@Component
class RedisPubSubAdapter(
    private val redisMessageListenerContainer: RedisMessageListenerContainer,
    private val qrCodeUUIDPort: QueryQRCodeUUIDPort,
    private val redisTemplate: RedisTemplate<String, Any>,
    private val tokenGeneratePort: TokenGeneratePort,
    private val tokenParsePort: TokenParsePort
): RedisPubSubPort {

    companion object {
        const val DEFAULT_TIMEOUT = 60L * 1000 * 10
    }

    /**
     * SSE 채널을 생성하고 Redis Lister Container 이벤트를 등록한다.
     */
    override fun createSseToRedisChanel(uuid: UUID): SseEmitter {
        // SSE 통신을 위한 SSE emitter 객체를 생성한다.
        val emitter = SseEmitter(TimeUnit.MINUTES.toMillis(DEFAULT_TIMEOUT))
        val listenerAdapter = MessageListenerAdapter(setMessageListener(uuid, emitter))
        redisMessageListenerContainer.addMessageListener(listenerAdapter, PatternTopic(uuid.toString()))

        log.info("Added emitter {} from listenerAdapter {}", emitter, listenerAdapter)

        sendToClient(emitter, "CONNECT", "CONNECT_$uuid", "SUCCESS_" + uuid + LocalDateTime.now())

        // SSE 연결이 종로 되었을 때 이벤트를 설정한다.
        emitter.onCompletion {
            log.info("Removed emitter {} from listenerAdapter {}", emitter, listenerAdapter)
            redisMessageListenerContainer.removeMessageListener(listenerAdapter)
        }

        return emitter
    }

    override fun pingCheck(uuid: UUID) {
        publicMessageToRedisChannel(uuid.toString(), "ping")
    }

    override fun sendTokenToSse(uuid: UUID, accessToken: String) {
        publicMessageToRedisChannel(uuid.toString(), accessToken)
    }

    private fun createToken(accessToken: String): TokenDto {
        val claim = tokenParsePort.getAccessTokenClaim(accessToken)
        val accountIdx = UUID.fromString(claim.subject)
        val authority = claim[JwtProperties.AUTHORITY]
        return tokenGeneratePort.generateToken(accountIdx, Authority.valueOf(authority.toString()))
    }

    private fun setMessageListener(uuid: UUID, emitter: SseEmitter): MessageListener {
        return MessageListener { message, pattern ->
            val replaceMessage = message.toString().replace("\"".toRegex(), "")
            if (replaceMessage == "ping") {
                sendPingMessage(uuid, emitter)
                return@MessageListener
            }
            sendToClient(emitter, "COMPLETE", "ping_$uuid", createToken(replaceMessage))
            // 성공했다는 메세지와 함께 새로 만든 토큰을 보낸다.
            log.debug("Received {} on {}", message, Thread.currentThread().name)
        }
    }

    // SSE 통신에서 데이터를 보내는 메서드이다.
    private fun sendToClient(emitter: SseEmitter, name: String, id: String, data: Any): Boolean {
        try {
            emitter.send(
                SseEmitter.event()
                    .id(id)
                    .name(name) // 클라이언트에서 메세지를 구분할 때 사용한다.
                    .data(data)
            )
        } catch (exception: IOException) {
            emitter.completeWithError(exception)
            log.info("연결이 종료되었습니다. 연결 및 데이터를 삭제합니다.")
            return false
        } catch (exception: IllegalStateException) {
            emitter.completeWithError(exception)
            log.info("연결이 종료되었습니다. 연결 및 데이터를 삭제합니다.")
            return false
        }
        return true
    }

    // 10초 간격으로 ping을 보내는 메서드이다.
    private fun sendPingMessage(uuid: UUID, emitter: SseEmitter) {
        val timer = Timer()
        val task = object : TimerTask() {
            override fun run() {
                if (!qrCodeUUIDPort.existByUUID(uuid)) {
                    timer.cancel()
                    sendToClient(emitter, "FAIL", "FAIL_$uuid", "FAIL_" + uuid + "_" + LocalDateTime.now())
                    emitter.complete()
                } else {
                    val eventKey = uuid.toString() + "_" + System.currentTimeMillis()
                    if (!sendToClient(emitter, "ping", "ping_$eventKey", LocalDateTime.now())) // 핑 메세지를 보낸후 성공 여부를 리턴받는다.
                        timer.cancel()
                }
            }
        }
        timer.schedule(task, 0L, 10000)
    }

    fun publicMessageToRedisChannel(channelName: String, message: String) {
        redisTemplate.convertAndSend(channelName, message)
    }

}