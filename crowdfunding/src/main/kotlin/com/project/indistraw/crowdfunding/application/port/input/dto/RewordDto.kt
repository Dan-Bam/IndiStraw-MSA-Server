package com.project.indistraw.crowdfunding.application.port.input.dto

data class RewordDto(
    val title: String,
    val description: String,
    val price: Long,
    val thumbnailUrl: String
)