package com.project.indistraw.thirdparty.rabbitmq.publisher

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component
import java.util.*

@Component
class RabbitMQCreateAccountPublisher(
    private val rabbitTemplate: RabbitTemplate
) {

    fun publish(accountIdx: UUID) {
        rabbitTemplate.convertAndSend(mapOf("accountIdx" to accountIdx))
    }

}