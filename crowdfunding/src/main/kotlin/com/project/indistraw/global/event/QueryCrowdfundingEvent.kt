package com.project.indistraw.global.event

import com.project.indistraw.crowdfunding.domain.Crowdfunding

data class QueryCrowdfundingEvent(
    val crowdfunding: Crowdfunding,
    val ip: String
)