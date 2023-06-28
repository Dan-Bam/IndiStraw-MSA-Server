package com.project.indistraw.crowdfunding.domain

import com.project.indistraw.crowdfunding.application.common.annotation.Aggregate
import java.math.BigDecimal

@Aggregate
data class Amount(
    val totalAmount: BigDecimal,
    val targetAmount: BigDecimal
)