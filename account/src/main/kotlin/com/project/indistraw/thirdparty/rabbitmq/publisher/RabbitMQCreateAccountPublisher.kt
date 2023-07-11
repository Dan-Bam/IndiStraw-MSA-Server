package com.project.indistraw.thirdparty.rabbitmq.publisher

import com.fasterxml.jackson.databind.ObjectMapper
import mu.KotlinLogging
import org.springframework.amqp.core.Message
import org.springframework.amqp.core.MessageProperties
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component
import java.util.*

private val log = KotlinLogging.logger {  }

@Component
class RabbitMQCreateAccountPublisher(
    private val rabbitTemplate: RabbitTemplate
) {

    fun publish(accountIdx: UUID) {
        log.info("accountIdx is $accountIdx")

        val properties = MessageProperties()
        properties.contentType = "create_account"

        val accountIdxToByte = ObjectMapper().writeValueAsString(mapOf("accountIdx" to accountIdx)).toByteArray()
        val message = Message(accountIdxToByte, properties)

        rabbitTemplate.convertAndSend("direct", "create_account", message)
    }

}