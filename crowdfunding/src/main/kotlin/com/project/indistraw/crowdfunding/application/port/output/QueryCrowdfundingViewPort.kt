package com.project.indistraw.crowdfunding.application.port.output

import com.project.indistraw.crowdfunding.domain.CrowdfundingViewCount

interface QueryCrowdfundingViewPort {

    fun existsByIdx(crowdfundingIdx: Long): Boolean
    fun findByIdx(crowdfundingIdx: Long): CrowdfundingViewCount

}