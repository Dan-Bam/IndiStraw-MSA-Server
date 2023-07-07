package com.project.indistraw

import mu.KotlinLogging
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import java.time.LocalDateTime
import java.util.*
import javax.annotation.PostConstruct

private val log = KotlinLogging.logger {  }

@ConfigurationPropertiesScan
@SpringBootApplication
class IndistrawAccountApplication {

	@PostConstruct
	fun applicationTimeZoneSetter() {
		val timeZone = TimeZone.getTimeZone("Asia/Seoul")
		TimeZone.setDefault(timeZone)

		log.info("IndiStraw-Account Application TimeZone was set: ${LocalDateTime.now()}")
	}

}

fun main(args: Array<String>) {
	runApplication<IndistrawAccountApplication>(*args)
}
