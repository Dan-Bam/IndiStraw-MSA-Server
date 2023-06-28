package com.project.indistraw.crowdfunding.application.port.input.dto

import java.math.BigDecimal

data class RewordDto(
    val idx: Long,
    val title: String,
    val description: String,
    val price: BigDecimal,
    val imageUrl: String
)