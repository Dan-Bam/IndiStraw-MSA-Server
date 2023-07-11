package com.project.indistraw.funding.application.port.input

import com.project.indistraw.funding.application.port.input.dto.SaveFundingDto

interface SaveFundingUseCase {

    fun execute(dto: SaveFundingDto)

}