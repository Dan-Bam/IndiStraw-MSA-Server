package com.project.indistraw.global.event

import com.project.indistraw.account.application.port.output.CommandAuthenticationPort
import com.project.indistraw.account.domain.Authentication
import mu.KotlinLogging
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

private val log = KotlinLogging.logger {  }

@Component
class CreateAuthenticationEventHandler(
    private val commandAuthenticationPort: CommandAuthenticationPort
) {

    @EventListener
    fun createAuthentication(createAuthenticationEvent: CreateAuthenticationEvent) {
        log.info("createAuthenticationEvent is activate")

        val authentication = Authentication(
            phoneNumber = createAuthenticationEvent.authentication.phoneNumber,
            attemptCount = createAuthenticationEvent.authentication.attemptCount,
            isVerified = createAuthenticationEvent.authentication.isVerified,
            expiredAt = createAuthenticationEvent.authentication.expiredAt
        )

        log.info("attemptCount is " + authentication.attemptCount)

        commandAuthenticationPort.saveAuthentication(authentication)
    }

}