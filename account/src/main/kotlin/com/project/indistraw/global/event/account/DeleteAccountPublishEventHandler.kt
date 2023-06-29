package com.project.indistraw.global.event.account

import com.project.indistraw.account.application.port.output.DeleteAccountPublishPort
import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

private val log = KotlinLogging.logger {  }

@Component
class DeleteAccountPublishEventHandler(
    private val deleteAccountPublishPort: DeleteAccountPublishPort
) {

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    fun deleteAccount(deleteAccountPublishEvent: DeleteAccountPublishEvent) {
        log.info("deleteAccountPublishEvent is active")
        deleteAccountPublishPort.execute(deleteAccountPublishEvent.accountIdx)
    }

}