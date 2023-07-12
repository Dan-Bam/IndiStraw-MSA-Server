package com.project.indistraw.crowdfunding.application.port.input.dto

data class AmountDto(
    val targetAmount: Long,
    val totalAmount: Long,
    val percentage: Double,
)