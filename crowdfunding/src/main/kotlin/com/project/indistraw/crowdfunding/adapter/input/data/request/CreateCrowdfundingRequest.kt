package com.project.indistraw.crowdfunding.adapter.input.data.request

import javax.validation.constraints.NotNull

data class CreateCrowdfundingRequest(
    @field:NotNull
    val title: String,

    @field:NotNull
    val description: String,

    @field:NotNull
    val targetAmount: Long,

    val directorAccount: DirectorAccountRequest,

    val reword: List<RewordRequest>,

    val date: DateRequest,

    @field:NotNull
    val imageList: List<String>,

    @field:NotNull
    val detailList: List<String>
)