package com.project.indistraw.account.adapter.output.publisher

import com.project.indistraw.account.application.port.output.CreateAccountPublishPort
import com.project.indistraw.account.domain.Account
import com.project.indistraw.thirdparty.rabbitmq.publisher.RabbitMQCreateAccountPublisher
import org.springframework.stereotype.Component

@Component
class CreateAccountPublishAdapter(
    private val rabbitMQCreateAccountPublisher: RabbitMQCreateAccountPublisher
): CreateAccountPublishPort {

    override fun execute(account: Account) {
        rabbitMQCreateAccountPublisher.publish(account)
    }

}