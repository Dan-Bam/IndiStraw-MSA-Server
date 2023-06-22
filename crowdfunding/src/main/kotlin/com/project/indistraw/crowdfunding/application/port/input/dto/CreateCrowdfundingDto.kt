package com.project.indistraw.crowdfunding.application.port.input.dto

import java.time.LocalDate

data class CreateCrowdfundingDto(
    val title: String,
    val description: String,
    val targetAmount: Long,
    val directorAccount: DirectorAccountDto,
    val reword: List<RewordDto>,
    val endDate: LocalDate,
    val thumbnailUrl: String,
    val imageList: List<String>,
    val detailList: List<String>
)