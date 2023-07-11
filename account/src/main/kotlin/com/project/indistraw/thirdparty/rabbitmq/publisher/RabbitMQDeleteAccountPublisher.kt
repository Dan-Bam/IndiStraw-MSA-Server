package com.project.indistraw.thirdparty.rabbitmq.publisher

import mu.KotlinLogging
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component
import java.util.*

private val log = KotlinLogging.logger {  }

@Component
class RabbitMQDeleteAccountPublisher(
    private val rabbitTemplate: RabbitTemplate
) {

    fun publish(accountIdx: UUID) {
        log.info("accountIdx is $accountIdx")
        rabbitTemplate.convertAndSend("topic", "delete_account", mapOf("accountIdx" to accountIdx))
    }

}