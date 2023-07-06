package com.project.indistraw.crowdfunding.adapter.input.data.response

import com.project.indistraw.crowdfunding.domain.StatusType

data class CrowdfundingListResponse(
    val idx: Long,
    val title: String,
    val description: String,
    val percentage: Double,
    val thumbnailUrl: String,
    val status: StatusType
)