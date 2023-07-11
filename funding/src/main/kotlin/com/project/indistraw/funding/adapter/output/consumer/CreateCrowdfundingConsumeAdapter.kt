package com.project.indistraw.funding.adapter.output.consumer

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.project.indistraw.funding.application.port.output.CommandCrowdfundingPort
import com.project.indistraw.funding.domain.Crowdfunding
import mu.KotlinLogging
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

private val log = KotlinLogging.logger {  }

@Component
class CreateCrowdfundingConsumeAdapter(
    private val commandCrowdfundingPort: CommandCrowdfundingPort
) {

    @RabbitListener(queues = ["crowdfunding"])
    fun listener(message: Message) {
        val objectMapper = ObjectMapper().registerModule(KotlinModule())
        val body = objectMapper.readTree(message.body)
        val crowdfunding = objectMapper.treeToValue(body, Crowdfunding::class.java)
        log.info("crowdfundingIdx is ${crowdfunding.idx}")

        commandCrowdfundingPort.saveCrowdfunding(crowdfunding)
    }

}