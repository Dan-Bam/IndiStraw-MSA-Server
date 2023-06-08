package com.project.indistraw.global.event

import com.project.indistraw.account.application.port.output.CommandAuthenticationPort
import com.project.indistraw.account.domain.Authentication
import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

private val log = KotlinLogging.logger {  }

@Component
class DeleteAuthenticationEventHandler(
    private val commandAuthenticationPort: CommandAuthenticationPort
) {

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT) // event publisher가 commit된 후 해당 event handler가 실행된다.
    fun deleteAuthentication(deleteAuthenticationEvent: DeleteAuthenticationEvent) {
        log.info("deleteAuthenticationEvent is activate")

        val authentication = Authentication(
            phoneNumber = deleteAuthenticationEvent.authentication.phoneNumber,
            attemptCount = deleteAuthenticationEvent.authentication.attemptCount,
            isVerified = deleteAuthenticationEvent.authentication.isVerified,
            expiredAt = deleteAuthenticationEvent.authentication.expiredAt
        )

        commandAuthenticationPort.deleteAuthentication(authentication)
    }

}