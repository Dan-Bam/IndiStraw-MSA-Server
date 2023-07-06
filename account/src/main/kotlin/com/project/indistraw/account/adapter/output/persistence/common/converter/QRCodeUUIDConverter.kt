package com.project.indistraw.account.adapter.output.persistence.common.converter

import com.project.indistraw.account.adapter.output.persistence.common.GenericConverter
import com.project.indistraw.account.adapter.output.persistence.entity.QRCodeUUIDEntity
import com.project.indistraw.account.domain.QRCodeUUID
import org.springframework.stereotype.Component

@Component
class QRCodeUUIDConverter: GenericConverter<QRCodeUUID, QRCodeUUIDEntity> {

    override infix fun toEntity(domain: QRCodeUUID): QRCodeUUIDEntity =
        domain.let {
            QRCodeUUIDEntity(
                uuid = it.uuid,
                expiredAt = it.expiredAt
            )
        }

    override infix fun toDomain(entity: QRCodeUUIDEntity?): QRCodeUUID? =
        entity?.let {
            QRCodeUUID(
                uuid = it.uuid,
                expiredAt = it.expiredAt
            )
        }

}