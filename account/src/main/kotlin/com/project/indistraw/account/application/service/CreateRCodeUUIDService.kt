package com.project.indistraw.account.application.service

import com.project.indistraw.account.application.common.annotation.ServiceWithTransaction
import com.project.indistraw.account.application.port.input.CreateQRCodeUUIDUseCase
import com.project.indistraw.account.application.port.output.CommandQRCodeUUIDPort
import com.project.indistraw.account.domain.QRCodeUUID
import java.util.*

@ServiceWithTransaction
class CreateRCodeUUIDService(
    private val commandQRCodeUUIDPort: CommandQRCodeUUIDPort
): CreateQRCodeUUIDUseCase {

    override fun execute(): UUID {
        val uuid = UUID.randomUUID()
        val qrCodeUUID = QRCodeUUID(
            uuid = uuid,
            expiredAt = 300L
        )
        commandQRCodeUUIDPort.saveQRCodeUUID(qrCodeUUID)

        return qrCodeUUID.uuid
    }

}