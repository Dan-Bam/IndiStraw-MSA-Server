package com.project.indistraw.crowdfunding.application.port.output

import com.project.indistraw.crowdfunding.domain.CrowdfundingViewCount

interface CommandCrowdfundingViewPort {

    fun saveCrowdfundingView(crowdfundingViewCount: CrowdfundingViewCount)

}