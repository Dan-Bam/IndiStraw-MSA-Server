package com.project.indistraw.crowdfunding.adapter.output.persistence

import com.project.indistraw.crowdfunding.adapter.output.persistence.common.converter.CrowdfundingConverter
import com.project.indistraw.crowdfunding.adapter.output.persistence.repository.CrowdfundingRepository
import com.project.indistraw.crowdfunding.application.port.output.CommandCrowdfundingPort
import com.project.indistraw.crowdfunding.domain.Crowdfunding
import org.springframework.stereotype.Component

@Component
class CommandCrowdfundingPersistenceAdapter(
    private val crowdfundingRepository: CrowdfundingRepository,
    private val crowdfundingConverter: CrowdfundingConverter
): CommandCrowdfundingPort {

    override fun saveCrowdfunding(crowdfunding: Crowdfunding): Crowdfunding {
        val crowdfundingEntity = crowdfundingConverter toEntity crowdfunding
        return (crowdfundingConverter toDomain crowdfundingRepository.save(crowdfundingEntity))!!
    }

}