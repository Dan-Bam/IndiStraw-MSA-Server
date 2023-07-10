package com.project.indistraw.funding.adapter.output.persistence

import com.project.indistraw.funding.adapter.output.persistence.common.converter.FundingConverter
import com.project.indistraw.funding.adapter.output.persistence.repository.FundingRepository
import com.project.indistraw.funding.application.port.output.CommandFundingPort
import com.project.indistraw.funding.domain.Funding
import org.springframework.stereotype.Component

@Component
class CommandFundingPersistenceAdapter(
    private val fundingConverter: FundingConverter,
    private val fundingRepository: FundingRepository
): CommandFundingPort {

    override fun saveFunding(funding: Funding) {
        val entity = fundingConverter.toEntity(funding)
        fundingRepository.save(entity)
    }

}