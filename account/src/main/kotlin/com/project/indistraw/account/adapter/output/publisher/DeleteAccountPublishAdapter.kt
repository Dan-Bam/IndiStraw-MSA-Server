package com.project.indistraw.account.adapter.output.publisher

import com.project.indistraw.account.application.port.output.DeleteAccountPublishPort
import com.project.indistraw.thirdparty.rabbitmq.publisher.RabbitMQDeleteAccountPublisher
import org.springframework.stereotype.Component
import java.util.*

@Component
class DeleteAccountPublishAdapter(
    private val rabbitMQDeleteAccountPublisher: RabbitMQDeleteAccountPublisher
): DeleteAccountPublishPort {

    override fun execute(accountIdx: UUID) {
        rabbitMQDeleteAccountPublisher.publish(accountIdx)
    }

}