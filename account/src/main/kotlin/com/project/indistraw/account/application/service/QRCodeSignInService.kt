package com.project.indistraw.account.application.service

import com.project.indistraw.account.application.common.annotation.ServiceWithReadOnlyTransaction
import com.project.indistraw.account.application.exception.AccountNotFoundException
import com.project.indistraw.account.application.exception.InvalidQRCodeUUIDException
import com.project.indistraw.account.application.port.input.QRCodeSignInUseCase
import com.project.indistraw.account.application.port.input.dto.QRCodeUUIDDto
import com.project.indistraw.account.application.port.output.AccountSecurityPort
import com.project.indistraw.account.application.port.output.QueryAccountPort
import com.project.indistraw.account.application.port.output.QueryQRCodeUUIDPort
import com.project.indistraw.account.application.port.output.SseEmitterPort

@ServiceWithReadOnlyTransaction
class QRCodeSignInService(
    private val queryQRCodeUUIDPort: QueryQRCodeUUIDPort,
    private val sseEmitterPort: SseEmitterPort,
    private val queryAccountPort: QueryAccountPort,
    private val accountSecurityPort: AccountSecurityPort
): QRCodeSignInUseCase {

    override fun execute(dto: QRCodeUUIDDto) {
        if (queryQRCodeUUIDPort.existByUUID(dto.uuid)) {
            throw InvalidQRCodeUUIDException()
        }
        val account = queryAccountPort.findByIdxOrNull(accountSecurityPort.getCurrentAccountIdx())
            ?: throw AccountNotFoundException()
        // uuid, account가 유효한지 확인 한 후 sse로 token을 보낸다.
        sseEmitterPort.sendTokenToSse(dto.uuid, account)
    }

}