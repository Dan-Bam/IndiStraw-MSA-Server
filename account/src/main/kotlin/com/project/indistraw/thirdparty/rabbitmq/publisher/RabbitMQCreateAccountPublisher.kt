package com.project.indistraw.thirdparty.rabbitmq.publisher

import com.project.indistraw.account.domain.Account
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component

@Component
class RabbitMQCreateAccountPublisher(
    private val rabbitTemplate: RabbitTemplate
) {

    fun publish(account: Account) {
        rabbitTemplate.convertAndSend(account)
    }

}