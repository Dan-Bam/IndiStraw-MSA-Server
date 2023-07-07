package com.project.indistraw.crowdfunding.adapter.input.data.response

data class RewardResponse(
    val idx: Long,
    val title: String,
    val description: String,
    val price: Long,
    val imageUrl: String,
    val totalCount: Long?
)