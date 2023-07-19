package com.project.indistraw.crowdfunding.adapter.output.consumer

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.project.indistraw.crowdfunding.application.port.output.CommandAccountPort
import com.project.indistraw.crowdfunding.domain.Account
import mu.KotlinLogging
import org.springframework.amqp.core.ExchangeTypes
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.annotation.Exchange
import org.springframework.amqp.rabbit.annotation.Queue
import org.springframework.amqp.rabbit.annotation.QueueBinding
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

private val log = KotlinLogging.logger {  }

@Component
class AccountConsumeAdapter(
    private val commandAccountPort: CommandAccountPort
) {

    @RabbitListener(
        bindings = [QueueBinding(
            value = Queue("crowdfunding", durable = "false"),
            exchange = Exchange(value = "direct", type = ExchangeTypes.DIRECT, durable = "false"),
            key = ["create_account"]
        )]
    )
    fun consumer(message: Message) {
        log.info("consuming start")
        when (message.messageProperties.contentType) {
            "create_account" -> {
                log.info("create account consuming")
                val account = getAccount(message)
                commandAccountPort.saveAccount(account)
            }
        }
    }

    private fun getAccount(message: Message): Account {
        val objectMapper = ObjectMapper().registerModule(KotlinModule.Builder().build())
        // 알 수 없는 필드 속성은 무시하도록 합니다.
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        // null을 빈 값으로 초기화 합니다.
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true)

        val body = objectMapper.readTree(message.body)
        return objectMapper.treeToValue(body, Account::class.java)
    }

}