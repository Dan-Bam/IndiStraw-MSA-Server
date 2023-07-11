package com.project.indistraw.funding.adapter.output.persistence

import com.project.indistraw.funding.adapter.output.persistence.common.converter.FundingConverter
import com.project.indistraw.funding.adapter.output.persistence.repository.FundingRepository
import com.project.indistraw.funding.application.port.output.QueryFundingPort
import com.project.indistraw.funding.domain.Funding
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class QueryFundingPersistenceAdapter(
    private val fundingConverter: FundingConverter,
    private val fundingRepository: FundingRepository
): QueryFundingPort {

    override fun findByIdx(idx: Long): Funding? {
        val fundingEntity = fundingRepository.findByIdOrNull(idx)
        return fundingConverter toDomain fundingEntity
    }

}