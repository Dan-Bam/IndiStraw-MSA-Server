package com.project.indistraw.account.application.service

import com.project.indistraw.account.application.common.annotation.ServiceWithTransaction
import com.project.indistraw.account.application.exception.AccountNotFoundException
import com.project.indistraw.account.application.exception.InvalidQRCodeUUIDException
import com.project.indistraw.account.application.port.input.QRCodeSignInUseCase
import com.project.indistraw.account.application.port.input.dto.QRCodeUUIDDto
import com.project.indistraw.account.application.port.output.AccountSecurityPort
import com.project.indistraw.account.application.port.output.QueryAccountPort
import com.project.indistraw.account.application.port.output.QueryQRCodeUUIDPort
import com.project.indistraw.account.application.port.output.SseEmitterPort
import com.project.indistraw.account.application.port.output.dto.TokenDto
import com.project.indistraw.account.domain.Account
import com.project.indistraw.global.event.SaveRefreshTokenEvent
import org.springframework.context.ApplicationEventPublisher

@ServiceWithTransaction
class QRCodeSignInService(
    private val queryQRCodeUUIDPort: QueryQRCodeUUIDPort,
    private val sseEmitterPort: SseEmitterPort,
    private val queryAccountPort: QueryAccountPort,
    private val accountSecurityPort: AccountSecurityPort,
    private val publisher: ApplicationEventPublisher
): QRCodeSignInUseCase {

    override fun execute(dto: QRCodeUUIDDto) {
        if (!queryQRCodeUUIDPort.existByUUID(dto.uuid)) {
            throw InvalidQRCodeUUIDException()
        }
        val account = queryAccountPort.findByIdxOrNull(accountSecurityPort.getCurrentAccountIdx())
            ?: throw AccountNotFoundException()
        // uuid, account가 유효한지 확인 한 후 sse로 token을 보낸다.
        val tokenDto = sseEmitterPort.sendTokenToSse(dto.uuid, account)
        publishSaveRefreshToken(tokenDto, account)
    }

    private fun publishSaveRefreshToken(token: TokenDto, account: Account) {
        val saveRefreshTokenEvent = SaveRefreshTokenEvent(
            refreshToken = token.refreshToken,
            accountIdx = account.accountIdx,
            expiredAt = token.refreshTokenExpiredAt
        )
        publisher.publishEvent(saveRefreshTokenEvent)
    }

}