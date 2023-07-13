package com.project.indistraw.crowdfunding.application.port.input

import com.project.indistraw.crowdfunding.application.port.input.dto.MyCrowdfundingDetailDto

interface MyCrowdfundingDetailUseCase {

    fun execute(idx: Long): MyCrowdfundingDetailDto

}