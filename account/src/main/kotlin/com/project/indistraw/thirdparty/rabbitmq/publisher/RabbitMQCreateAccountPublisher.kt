package com.project.indistraw.thirdparty.rabbitmq.publisher

import mu.KotlinLogging
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
        rabbitTemplate.convertAndSend("topic", "create_account", "success")
    }

}