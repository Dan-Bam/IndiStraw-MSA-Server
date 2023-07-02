package com.project.indistraw.crowdfunding.adapter.input.data.response

import java.math.BigDecimal

data class RewardResponse(
    val idx: Long,
    val title: String,
    val description: String,
    val price: BigDecimal,
    val imageUrl: String,
    val totalCount: Long?
)