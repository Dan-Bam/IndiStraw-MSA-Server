package com.project.indistraw.account.adapter.output.persistence

import com.project.indistraw.account.adapter.output.persistence.common.converter.QRCodeUUIDConverter
import com.project.indistraw.account.adapter.output.persistence.repository.QRCodeUUIDRepository
import com.project.indistraw.account.application.port.output.CommandQRCodeUUIDPort
import com.project.indistraw.account.domain.QRCodeUUID
import org.springframework.stereotype.Component

@Component
class CommandQRCodeUUIDPersistenceAdapter(
    private val qrCodeUUIDConverter: QRCodeUUIDConverter,
    private val qrCodeUUIDRepository: QRCodeUUIDRepository
): CommandQRCodeUUIDPort {

    override fun saveQRCodeUUID(domain: QRCodeUUID) {
        val qrCodeUUIDEntity = qrCodeUUIDConverter toEntity domain
        qrCodeUUIDRepository.save(qrCodeUUIDEntity)
    }

}