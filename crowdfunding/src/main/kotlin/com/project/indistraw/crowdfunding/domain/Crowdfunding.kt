package com.project.indistraw.crowdfunding.domain

import java.util.*

data class Crowdfunding(
    val idx: Long,
    val accountIdx: UUID,
    val title: String,
    val description: String,
    val amount: Amount,
    val directorAccount: DirectorAccount,
    val thumbnailUrl: String,
    val date: Date,
    val activity: Activity,
    val imageList: List<String>,
    val detailList: List<String>
)