package com.project.indistraw.global.event

import com.project.indistraw.account.application.port.output.CommandAuthenticationPort
import com.project.indistraw.account.domain.Authentication
import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

private val log = KotlinLogging.logger {  }

@Component
class CreateAuthenticationEventHandler(
    private val commandAuthenticationPort: CommandAuthenticationPort
) {

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT) // event publisher가 commit된 후 해당 event handler가 실행된다.
    fun createAuthentication(createAuthenticationEvent: CreateAuthenticationEvent) {
        log.info("createAuthentication is activate")

        val authentication = Authentication(
            phoneNumber = createAuthenticationEvent.phoneNumber,
            attemptCount = createAuthenticationEvent.attemptCount,
            isVerified = createAuthenticationEvent.isVerified,
            expiredAt = createAuthenticationEvent.expiredAt
        )

        log.info("isVerified is " + createAuthenticationEvent.isVerified)

        commandAuthenticationPort.saveAuthentication(authentication)
    }

}