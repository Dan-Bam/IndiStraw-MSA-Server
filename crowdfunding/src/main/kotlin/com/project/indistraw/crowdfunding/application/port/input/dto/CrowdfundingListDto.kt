package com.project.indistraw.crowdfunding.application.port.input.dto

import com.project.indistraw.crowdfunding.domain.StatusType
import java.math.BigDecimal

data class CrowdfundingListDto(
    val idx: Long,
    val title: String,
    val description: String,
    val percentage: BigDecimal,
    val thumbnailUrl: String,
    val statusType: StatusType
)