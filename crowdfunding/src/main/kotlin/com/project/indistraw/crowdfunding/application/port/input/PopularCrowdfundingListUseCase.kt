package com.project.indistraw.crowdfunding.application.port.input

import com.project.indistraw.crowdfunding.application.port.input.dto.CrowdfundingListDto

interface PopularCrowdfundingListUseCase {

    fun execute(): List<CrowdfundingListDto>

}