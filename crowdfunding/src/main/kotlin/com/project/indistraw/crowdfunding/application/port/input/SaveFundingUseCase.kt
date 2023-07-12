package com.project.indistraw.crowdfunding.application.port.input

import com.project.indistraw.crowdfunding.application.port.input.dto.SaveFundingDto

interface SaveFundingUseCase {

    fun execute(dto: SaveFundingDto)

}