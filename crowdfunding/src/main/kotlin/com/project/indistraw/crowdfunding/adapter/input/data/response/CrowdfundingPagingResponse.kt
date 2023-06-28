package com.project.indistraw.crowdfunding.adapter.input.data.response

data class CrowdfundingPagingResponse(
    val pageSize: Int,
    val isLast: Boolean,
    val list: List<CrowdfundingListResponse>
)