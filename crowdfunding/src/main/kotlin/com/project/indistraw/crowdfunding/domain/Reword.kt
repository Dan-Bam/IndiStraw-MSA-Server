package com.project.indistraw.crowdfunding.domain

import com.project.indistraw.crowdfunding.application.common.annotation.Aggregate
import java.math.BigDecimal

@Aggregate
data class Reword(
    val idx: Long,
    val title: String,
    val description: String,
    val price: BigDecimal,
    val imageUrl: String,
    val crowdfundingIdx: Long
)