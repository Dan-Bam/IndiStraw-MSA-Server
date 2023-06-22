package com.project.indistraw.crowdfunding.domain

import com.project.indistraw.crowdfunding.application.common.annotation.Aggregate

@Aggregate
data class DirectorAccount(
    val bank: String,
    val account: String
)