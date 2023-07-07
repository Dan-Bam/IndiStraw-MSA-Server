package com.project.indistraw.crowdfunding.application.port.input.dto

data class CrowdfundingPagingDto(
    val isLast: Boolean,
    val list: List<CrowdfundingListDto>
)