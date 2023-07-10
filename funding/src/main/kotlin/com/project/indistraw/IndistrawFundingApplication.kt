package com.project.indistraw

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication
class IndistrawFundingApplication

fun main(args: Array<String>) {
    runApplication<IndistrawFundingApplication>(*args)
}
