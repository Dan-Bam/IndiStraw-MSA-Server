package com.project.indistraw.crowdfunding.domain

import java.math.BigDecimal

data class Amount(
    val totalAmount: BigDecimal,
    val targetAmount: BigDecimal
)