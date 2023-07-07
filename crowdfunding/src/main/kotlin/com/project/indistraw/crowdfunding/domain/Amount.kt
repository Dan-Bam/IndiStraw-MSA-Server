package com.project.indistraw.crowdfunding.domain

import com.project.indistraw.crowdfunding.application.common.annotation.Aggregate

@Aggregate
data class Amount(
    val totalAmount: Long,
    val targetAmount: Long
)