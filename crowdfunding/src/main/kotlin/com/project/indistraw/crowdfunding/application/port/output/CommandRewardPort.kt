package com.project.indistraw.crowdfunding.application.port.output

import com.project.indistraw.crowdfunding.domain.Reward

interface CommandRewardPort {

    fun saveReward(reward: Reward)

    fun saveAllReword(rewordList: List<Reward>)

}