package com.project.indistraw.funding.adapter.output.persistence.common.converter

import com.project.indistraw.funding.adapter.output.persistence.common.GenericConverter
import com.project.indistraw.funding.adapter.output.persistence.entity.FundingEntity
import com.project.indistraw.funding.domain.Funding
import org.springframework.stereotype.Component

@Component
class FundingConverter: GenericConverter<Funding, FundingEntity> {

    override infix fun toEntity(domain: Funding): FundingEntity =
        domain.let {
            FundingEntity(
                idx = it.idx,
                crowdfundingIdx = it.crowdfundingIdx,
                rewordIdx = it.rewordIdx,
                ordererIdx = it.ordererIdx,
                price = it.price,
                extraPrice = it.extraPrice,
                activity = it.activity
            )
        }

    override infix fun toDomain(entity: FundingEntity?): Funding? =
        entity?.let {
            Funding(
                idx = it.idx,
                crowdfundingIdx = it.crowdfundingIdx,
                rewordIdx = it.rewordIdx,
                ordererIdx = it.ordererIdx,
                price = it.price,
                extraPrice = it.extraPrice,
                activity = it.activity
            )
        }

}