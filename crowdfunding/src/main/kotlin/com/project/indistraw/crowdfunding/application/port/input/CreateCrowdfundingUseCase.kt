package com.project.indistraw.crowdfunding.application.port.input

import com.project.indistraw.crowdfunding.application.port.input.dto.CreateCrowdfundingDto

interface CreateCrowdfundingUseCase {

    fun execute(dto: CreateCrowdfundingDto)

}