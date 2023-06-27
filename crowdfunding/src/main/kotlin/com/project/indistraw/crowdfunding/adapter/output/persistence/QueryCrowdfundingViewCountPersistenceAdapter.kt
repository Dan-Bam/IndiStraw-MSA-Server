package com.project.indistraw.crowdfunding.adapter.output.persistence

import com.project.indistraw.crowdfunding.adapter.output.persistence.common.converter.CrowdfundingViewCountConverter
import com.project.indistraw.crowdfunding.adapter.output.persistence.repository.CrowdfundingViewCountRepository
import com.project.indistraw.crowdfunding.application.port.output.QueryCrowdfundingViewPort
import com.project.indistraw.crowdfunding.domain.CrowdfundingViewCount
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class QueryCrowdfundingViewCountPersistenceAdapter(
    private val crowdfundingViewCountConverter: CrowdfundingViewCountConverter,
    private val crowdfundingViewCountRepository: CrowdfundingViewCountRepository
): QueryCrowdfundingViewPort {

    override fun existsByIdx(crowdfundingIdx: Long): Boolean {
        return crowdfundingViewCountRepository.existsById(crowdfundingIdx)
    }

    override fun findByIdx(crowdfundingIdx: Long): CrowdfundingViewCount {
        val crowdfundingViewCountEntity = crowdfundingViewCountRepository.findByIdOrNull(crowdfundingIdx)!!
        return crowdfundingViewCountConverter toDomain crowdfundingViewCountEntity
    }

}