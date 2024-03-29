package com.project.indistraw.global.event

import com.project.indistraw.account.application.port.output.CommandRefreshTokenPort
import com.project.indistraw.account.domain.RefreshToken
import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

private val log = KotlinLogging.logger {  }

@Component
class SaveRefreshTokenEventHandler(
    private val commandRefreshTokenPort: CommandRefreshTokenPort
) {

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    fun saveRefreshToken(saveRefreshTokenEvent: SaveRefreshTokenEvent) {
        log.info("saveRefreshTokenEvent is activate")

        val refreshToken = RefreshToken(
            refreshToken = saveRefreshTokenEvent.refreshToken,
            accountIdx = saveRefreshTokenEvent.accountIdx,
            expiredAt = saveRefreshTokenEvent.expiredAt
        )

        commandRefreshTokenPort.saveRefreshToken(refreshToken)
    }

}