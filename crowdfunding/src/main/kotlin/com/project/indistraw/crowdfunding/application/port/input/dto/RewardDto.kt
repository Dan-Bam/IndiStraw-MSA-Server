package com.project.indistraw.crowdfunding.application.port.input.dto

data class RewardDto(
    val idx: Long,
    val title: String,
    val description: String,
    val price: Long,
    val imageList: List<String>,
    val totalCount: Long?
)
