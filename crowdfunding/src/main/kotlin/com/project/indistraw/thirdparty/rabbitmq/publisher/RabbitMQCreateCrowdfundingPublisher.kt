package com.project.indistraw.thirdparty.rabbitmq.publisher

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component

@Component
class RabbitMQCreateCrowdfundingPublisher(
    private val rabbitTemplate: RabbitTemplate
) {

    fun publish() {
        rabbitTemplate.convertAndSend("")
    }

}