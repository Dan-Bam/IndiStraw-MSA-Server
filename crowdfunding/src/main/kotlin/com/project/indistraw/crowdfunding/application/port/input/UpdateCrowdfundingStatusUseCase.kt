package com.project.indistraw.crowdfunding.application.port.input

import com.project.indistraw.crowdfunding.domain.StatusType

interface UpdateCrowdfundingStatusUseCase {

    fun execute(idx: Long, statusType: StatusType)

}