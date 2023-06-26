package com.project.indistraw.crowdfunding.application.port.input.dto

data class CrowdfundingPagingDto(
    val pageSize: Int,
    val isLast: Boolean,
    val list: List<CrowdfundingListDto>
)