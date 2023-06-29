package com.project.indistraw.global.event.account

import com.project.indistraw.account.application.port.output.CreateAccountPublishPort
import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

private val log = KotlinLogging.logger {  }

@Component
class CreateAccountPublishEventHandler(
    private val crateAccountPublishPort: CreateAccountPublishPort
) {

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    fun createAccount(createAccountPublishEvent: CreateAccountPublishEvent) {
        log.info("createAccountPublishEvent is active")
        crateAccountPublishPort.execute(createAccountPublishEvent.account)
    }

}