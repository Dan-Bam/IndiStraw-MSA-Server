package com.project.indistraw.crowdfunding.adapter.output.persistence

import com.project.indistraw.crowdfunding.adapter.output.persistence.common.converter.RewardConverter
import com.project.indistraw.crowdfunding.adapter.output.persistence.repository.RewardRepository
import com.project.indistraw.crowdfunding.application.port.output.CommandRewardPort
import com.project.indistraw.crowdfunding.domain.Reward
import org.springframework.stereotype.Component

@Component
class CommandRewardPersistenceAdapter(
    private val rewordConverter: RewardConverter,
    private val rewardRepository: RewardRepository
): CommandRewardPort {

    override fun saveReward(reward: Reward) {
        val rewardEntity = rewordConverter toEntity reward
        rewardRepository.save(rewardEntity)
    }

    override fun saveAllReword(rewordList: List<Reward>) {
        val rewordEntityList = rewordConverter toEntityList rewordList
        rewardRepository.saveAll(rewordEntityList)
    }

}