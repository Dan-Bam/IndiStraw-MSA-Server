package com.project.indistraw.crowdfunding.adapter.output.publisher

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.project.indistraw.crowdfunding.application.port.output.CrowdfundingPublishPort
import com.project.indistraw.crowdfunding.domain.Crowdfunding
import mu.KotlinLogging
import org.springframework.amqp.core.Message
import org.springframework.amqp.core.MessageProperties
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component

private val log = KotlinLogging.logger {  }

@Component
class CrowdfundingPublishAdapter(
    private val rabbitTemplate: RabbitTemplate
): CrowdfundingPublishPort {

    override fun create(crowdfunding: Crowdfunding) {
        log.info("crowdfundingIdx is ${crowdfunding.idx}")

        val properties = MessageProperties()
        properties.contentType = "create_crowdfunding"

        val objectMapper = ObjectMapper()
        objectMapper.registerModule(JavaTimeModule())
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)

        val crowdfundingTitle = objectMapper.writeValueAsString(mapOf("tittle" to crowdfunding.title)).toByteArray()
        val message = Message(crowdfundingTitle, properties)
        rabbitTemplate.convertAndSend("direct", "create_crowdfunding", message)
    }

}