package com.project.indistraw.crowdfunding.application.port.output

import com.project.indistraw.crowdfunding.application.port.input.dto.CrowdfundingListDto

interface SearchCrowdfundingUseCase {

    fun execute(keyword: String?): List<CrowdfundingListDto>

}