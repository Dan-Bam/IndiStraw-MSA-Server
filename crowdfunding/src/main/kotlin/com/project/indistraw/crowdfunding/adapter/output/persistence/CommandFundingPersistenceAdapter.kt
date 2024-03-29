package com.project.indistraw.crowdfunding.adapter.output.persistence

import com.project.indistraw.crowdfunding.adapter.output.persistence.common.converter.FundingConverter
import com.project.indistraw.crowdfunding.adapter.output.persistence.repository.FundingRepository
import com.project.indistraw.crowdfunding.application.port.output.CommandFundingPort
import com.project.indistraw.crowdfunding.domain.Funding
import org.springframework.stereotype.Component

@Component
class CommandFundingPersistenceAdapter(
    private val fundingConverter: FundingConverter,
    private val fundingRepository: FundingRepository
): CommandFundingPort {

    override fun saveFunding(funding: Funding) {
        val entity = fundingConverter toEntity funding
        fundingRepository.save(entity)
    }

    override fun deleteFunding(funding: Funding) {
        val entity = fundingConverter toEntity funding
        fundingRepository.delete(entity)
    }

}