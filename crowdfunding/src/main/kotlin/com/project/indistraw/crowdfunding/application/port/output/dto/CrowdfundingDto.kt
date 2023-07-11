package com.project.indistraw.crowdfunding.application.port.output.dto

data class CrowdfundingDto(
    val idx: Long,
    val title: String,
    val description: String,
    var status: String,
    val thumbnailUrl: String
)