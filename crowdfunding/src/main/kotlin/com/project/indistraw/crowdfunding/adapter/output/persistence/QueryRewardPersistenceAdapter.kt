package com.project.indistraw.crowdfunding.adapter.output.persistence

import com.project.indistraw.crowdfunding.adapter.output.persistence.common.converter.RewardConverter
import com.project.indistraw.crowdfunding.adapter.output.persistence.repository.CrowdfundingRepository
import com.project.indistraw.crowdfunding.adapter.output.persistence.repository.RewardRepository
import com.project.indistraw.crowdfunding.application.exception.CrowdfundingNotFoundException
import com.project.indistraw.crowdfunding.application.port.output.QueryRewardPort
import com.project.indistraw.crowdfunding.domain.Reward
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class QueryRewardPersistenceAdapter(
    private val rewordConverter: RewardConverter,
    private val crowdfundingRepository: CrowdfundingRepository,
    private val rewardRepository: RewardRepository
): QueryRewardPort {

    override fun findByCrowdfundingIdx(crowdfundingIdx: Long): List<Reward> {
        val crowdfundingEntity = crowdfundingRepository.findByIdOrNull(crowdfundingIdx)
            ?: throw CrowdfundingNotFoundException()
        val rewardList = rewardRepository.findByCrowdfundingEntity(crowdfundingEntity)
        return rewordConverter toDomainList rewardList
    }

}