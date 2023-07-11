package com.project.indistraw.funding.adapter.output.persistence

import com.project.indistraw.funding.adapter.output.persistence.common.converter.CrowdfundingConverter
import com.project.indistraw.funding.adapter.output.persistence.repository.CrowdfundingRepository
import com.project.indistraw.funding.application.port.output.CommandCrowdfundingPort
import com.project.indistraw.funding.domain.Crowdfunding
import org.springframework.stereotype.Component

@Component
class CommandCrowdfundingPersistenceAdapter(
    private val crowdfundingRepository: CrowdfundingRepository,
    private val crowdfundingConverter: CrowdfundingConverter
): CommandCrowdfundingPort {

    override fun saveCrowdfunding(crowdfunding: Crowdfunding): Long {
        val crowdfundingEntity = crowdfundingConverter toEntity crowdfunding
        return crowdfundingRepository.save(crowdfundingEntity).idx
    }

}