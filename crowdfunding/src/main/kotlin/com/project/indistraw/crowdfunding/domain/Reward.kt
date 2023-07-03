package com.project.indistraw.crowdfunding.domain

import com.project.indistraw.crowdfunding.application.common.annotation.Aggregate

@Aggregate
data class Reward(
    val idx: Long,
    val crowdfundingIdx: Long,
    val title: String,
    val description: String,
    val price: Long,
    val imageUrl: String,
    val totalCount: Long?
)