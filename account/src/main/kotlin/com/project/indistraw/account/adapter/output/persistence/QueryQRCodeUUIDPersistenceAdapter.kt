package com.project.indistraw.account.adapter.output.persistence

import com.project.indistraw.account.adapter.output.persistence.common.converter.QRCodeUUIDConverter
import com.project.indistraw.account.adapter.output.persistence.repository.QRCodeUUIDRepository
import com.project.indistraw.account.application.port.output.QueryQRCodeUUIDPort
import com.project.indistraw.account.domain.QRCodeUUID
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.*

@Component
class QueryQRCodeUUIDPersistenceAdapter(
    private val qrCodeUUIDConverter: QRCodeUUIDConverter,
    private val qrCodeUUIDRepository: QRCodeUUIDRepository
): QueryQRCodeUUIDPort {

    override fun findByUUID(uuid: UUID): QRCodeUUID? {
        val qrCodeUUIDEntity = qrCodeUUIDRepository.findByIdOrNull(uuid)
        return qrCodeUUIDConverter toDomain qrCodeUUIDEntity
    }

    override fun existByUUID(uuid: UUID): Boolean {
        return qrCodeUUIDRepository.existsById(uuid)
    }

}