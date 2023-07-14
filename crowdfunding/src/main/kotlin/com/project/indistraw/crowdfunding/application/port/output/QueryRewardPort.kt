package com.project.indistraw.crowdfunding.application.port.output

import com.project.indistraw.crowdfunding.domain.Reward

interface QueryRewardPort {

    fun findByIdx(idx: Long): Reward?
    fun findByCrowdfundingIdx(crowdfundingIdx: Long): List<Reward>

}