package com.project.indistraw.crowdfunding.adapter.output.persistence

import com.project.indistraw.crowdfunding.adapter.output.persistence.common.converter.CrowdfundingViewCountConverter
import com.project.indistraw.crowdfunding.adapter.output.persistence.repository.CrowdfundingViewCountRepository
import com.project.indistraw.crowdfunding.application.port.output.CommandCrowdfundingViewPort
import com.project.indistraw.crowdfunding.domain.CrowdfundingViewCount
import org.springframework.stereotype.Component

@Component
class CommandCrowdfundingViewCountPersistenceAdapter(
    private val crowdfundingViewCountConverter: CrowdfundingViewCountConverter,
    private val crowdfundingViewCountRepository: CrowdfundingViewCountRepository
): CommandCrowdfundingViewPort {

    override fun saveCrowdfundingView(crowdfundingViewCount: CrowdfundingViewCount) {
        val crowdfundingVieCountEntity = crowdfundingViewCountConverter toEntity crowdfundingViewCount
        crowdfundingViewCountRepository.save(crowdfundingVieCountEntity)
    }

}