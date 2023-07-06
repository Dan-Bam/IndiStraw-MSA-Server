package com.project.indistraw.crowdfunding.application.port.output.dto

import java.math.BigDecimal

data class CrowdfundingInfoDto(
    val crowdfundingIdx: Long,
    val title: String,
    val description: String,
    val percentage: BigDecimal,
    val thumbnailUrl: String
)