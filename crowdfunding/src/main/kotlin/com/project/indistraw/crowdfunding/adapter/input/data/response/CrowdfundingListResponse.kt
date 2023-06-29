package com.project.indistraw.crowdfunding.adapter.input.data.response

import com.project.indistraw.crowdfunding.domain.StatusType
import java.math.BigDecimal

data class CrowdfundingListResponse(
    val idx: Long,
    val title: String,
    val description: String,
    val percentage: BigDecimal,
    val thumbnailUrl: String,
    val status: StatusType
)