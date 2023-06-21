package com.project.indistraw.crowdfunding.application.port.input.dto

data class CreateCrowdfundingDto(
    val title: String,
    val description: String,
    val targetAmount: Long,
    val directorAccount: DirectorAccountDto,
    val reword: List<RewordDto>,
    val date: DateDto,
    val thumbnailUrl: String,
    val imageList: List<String>,
    val detailList: List<String>
)