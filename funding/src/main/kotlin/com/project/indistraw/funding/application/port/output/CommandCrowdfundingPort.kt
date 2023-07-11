package com.project.indistraw.funding.application.port.output

import com.project.indistraw.funding.domain.Crowdfunding

interface CommandCrowdfundingPort {

    fun saveCrowdfunding(crowdfunding: Crowdfunding): Long

}