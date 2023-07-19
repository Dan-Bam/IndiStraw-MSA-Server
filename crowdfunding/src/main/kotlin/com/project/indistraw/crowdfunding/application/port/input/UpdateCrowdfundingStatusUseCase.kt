package com.project.indistraw.crowdfunding.application.port.input

import com.project.indistraw.crowdfunding.domain.Crowdfunding

interface UpdateCrowdfundingStatusUseCase {

    fun execute(idx: Long, statusType: Crowdfunding.StatusType)

}