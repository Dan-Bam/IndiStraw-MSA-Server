package com.project.indistraw.crowdfunding.adapter.input.data.request

import javax.validation.constraints.NotNull

data class RewordRequest(
    @field:NotNull
    val title: String,

    @field:NotNull
    val description: String,

    @field:NotNull
    val price: Long,

    @field:NotNull
    val imageUrl: String
)