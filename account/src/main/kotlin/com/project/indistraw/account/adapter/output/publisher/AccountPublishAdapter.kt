package com.project.indistraw.account.adapter.output.publisher

import com.fasterxml.jackson.databind.ObjectMapper
import com.project.indistraw.account.application.port.input.dto.AccountInfoDto
import com.project.indistraw.account.application.port.output.AccountPublishPort
import com.project.indistraw.account.domain.Account
import mu.KotlinLogging
import org.springframework.amqp.core.Message
import org.springframework.amqp.core.MessageProperties
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component
import java.util.*

private val log = KotlinLogging.logger { }

@Component
class AccountPublishAdapter(
    private val rabbitTemplate: RabbitTemplate,
): AccountPublishPort {

    override fun create(account: Account) {
        log.info("accountIdx is ${account.accountIdx}")

        val properties = MessageProperties()
        properties.contentType = "create_account"

        val accountIdxToByte = ObjectMapper().writeValueAsString(initAccount(account)).toByteArray()
        val message = Message(accountIdxToByte, properties)

        rabbitTemplate.convertAndSend("direct", "create_account", message)
    }

    override fun delete(accountIdx: UUID) {
        log.info("accountIdx is $accountIdx")

        val properties = MessageProperties()
        properties.contentType = "delete_account"

        val accountIdxToByte = ObjectMapper().writeValueAsString(mapOf("accountIdx" to accountIdx)).toByteArray()
        val message = Message(accountIdxToByte, properties)

        rabbitTemplate.convertAndSend("direct", "create_account", message)
    }

    private fun initAccount(account: Account) =
        AccountInfoDto(
            accountIdx = account.accountIdx,
            id = account.id,
            name = account.name,
            phoneNumber = account.phoneNumber,
            zipcode = account.address?.zipcode,
            address = account.address?.let {
                if (it.zipcode.isNullOrBlank()) null
                else it.streetAddress + " " + it.detailAddress
            },
            profileUrl = account.profileUrl
        )

}