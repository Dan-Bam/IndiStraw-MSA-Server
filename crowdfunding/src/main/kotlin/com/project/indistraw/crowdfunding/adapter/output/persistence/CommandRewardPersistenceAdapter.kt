package com.project.indistraw.crowdfunding.adapter.output.persistence

import com.project.indistraw.crowdfunding.adapter.output.persistence.common.converter.RewordConverter
import com.project.indistraw.crowdfunding.adapter.output.persistence.repository.RewardRepository
import com.project.indistraw.crowdfunding.application.port.output.CommandRewordPort
import com.project.indistraw.crowdfunding.domain.Reward
import org.springframework.stereotype.Component

@Component
class CommandRewardPersistenceAdapter(
    private val rewordConverter: RewordConverter,
    private val rewardRepository: RewardRepository
): CommandRewordPort {

    override fun saveAllReword(rewordList: List<Reward>) {
        val rewordEntityList = rewordConverter toEntityList rewordList
        rewardRepository.saveAll(rewordEntityList)
    }

}