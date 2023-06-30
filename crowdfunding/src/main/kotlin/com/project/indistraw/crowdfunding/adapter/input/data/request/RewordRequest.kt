package com.project.indistraw.crowdfunding.adapter.input.data.request

import java.math.BigDecimal
import javax.validation.constraints.NotNull

data class RewordRequest(
    @field:NotNull
    val title: String,

    @field:NotNull
    val description: String,

    @field:NotNull
    val price: BigDecimal,

    @field:NotNull
    val imageUrl: String,

    val totalCount: Long
)