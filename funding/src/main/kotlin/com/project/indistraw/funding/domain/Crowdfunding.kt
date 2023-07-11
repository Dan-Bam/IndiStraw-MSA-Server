package com.project.indistraw.funding.domain

data class Crowdfunding(
    val idx: Long,
    val title: String,
    val description: String,
    var status: String,
    val thumbnailUrl: String
)