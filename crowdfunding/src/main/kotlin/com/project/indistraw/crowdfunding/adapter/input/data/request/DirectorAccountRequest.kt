package com.project.indistraw.crowdfunding.adapter.input.data.request

import javax.validation.constraints.NotNull

data class DirectorAccountRequest(
    @field:NotNull
    val bank: String,

    @field:NotNull
    val account: String
)