package com.project.indistraw.account.application.service

import com.project.indistraw.account.application.port.input.ConnectSseUseCase
import com.project.indistraw.account.application.port.output.RedisPubSubPort
import org.springframework.stereotype.Service
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.util.UUID

@Service
class ConnectSseService(
    private val redisPubSubPort: RedisPubSubPort
): ConnectSseUseCase {

    override fun execute(uuid: UUID): SseEmitter {
        // 생성된 uuid를 key로 redis sse 채널을 생성한다.
        return redisPubSubPort.createSseToRedisChanel(uuid)
    }

}