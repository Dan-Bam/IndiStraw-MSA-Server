package com.project.indistraw.crowdfunding.adapter.output.persistence.common.converter

import com.project.indistraw.crowdfunding.adapter.output.persistence.common.GenericConverter
import com.project.indistraw.crowdfunding.adapter.output.persistence.entity.PayInfoEntity
import com.project.indistraw.crowdfunding.domain.PayInfo
import org.springframework.stereotype.Component

@Component
class PayInfoConverter: GenericConverter<PayInfo, PayInfoEntity> {

    override infix fun toEntity(domain: PayInfo): PayInfoEntity =
        domain.let {
            PayInfoEntity(
                id = it.receiptId,
                accountIdx = it.accountIdx
            )
        }

    override infix fun toDomain(entity: PayInfoEntity?): PayInfo? =
        entity?.let {
            PayInfo(
                receiptId = it.id,
                accountIdx = it.accountIdx
            )
        }

}