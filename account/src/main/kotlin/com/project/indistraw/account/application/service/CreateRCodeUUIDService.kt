package com.project.indistraw.account.application.service

import com.project.indistraw.account.application.common.annotation.ServiceWithTransaction
import com.project.indistraw.account.application.port.input.CreateQRCodeUUIDUseCase
import com.project.indistraw.account.application.port.output.CommandQRCodeUUIDPort
import com.project.indistraw.account.application.port.output.RedisPubSubPort
import com.project.indistraw.account.domain.QRCodeUUID
import java.util.*

@ServiceWithTransaction
class CreateRCodeUUIDService(
    private val commandQRCodeUUIDPort: CommandQRCodeUUIDPort,
    private val redisPubSubPort: RedisPubSubPort
): CreateQRCodeUUIDUseCase {

    override fun execute(): UUID {
        val uuid = UUID.randomUUID()
        val qrCodeUUID = QRCodeUUID(
            uuid = uuid,
            expiredAt = 180L
        )
        commandQRCodeUUIDPort.saveQRCodeUUID(qrCodeUUID)
        // 생성된 uuid를 key로 redis sse 채널을 생성한다.
        redisPubSubPort.createSseToRedisChanel(qrCodeUUID.uuid)
        return qrCodeUUID.uuid
    }

}