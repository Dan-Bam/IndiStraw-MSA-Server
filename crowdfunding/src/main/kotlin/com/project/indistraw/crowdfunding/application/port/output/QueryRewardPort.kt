package com.project.indistraw.crowdfunding.application.port.output

import com.project.indistraw.crowdfunding.domain.Reward

interface QueryRewardPort {

    fun findByCrowdfundingIdx(crowdfundingIdx: Long): List<Reward>

}