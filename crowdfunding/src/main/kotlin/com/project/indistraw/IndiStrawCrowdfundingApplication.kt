package com.project.indistraw

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication
class IndiStrawCrowdfundingApplication

fun main(args: Array<String>) {
	runApplication<IndiStrawCrowdfundingApplication>(*args)
}
