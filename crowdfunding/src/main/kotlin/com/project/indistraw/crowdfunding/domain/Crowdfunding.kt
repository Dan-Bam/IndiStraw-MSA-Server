package com.project.indistraw.crowdfunding.domain

import com.project.indistraw.crowdfunding.application.common.annotation.AggregateRoot
import java.util.*

@AggregateRoot
data class Crowdfunding(
    val idx: Long,
    val accountIdx: UUID,
    val title: String,
    val description: String,
    val amount: Amount,
    val directorAccount: DirectorAccount,
    val date: Date,
    val activity: Activity,
    val thumbnailUrl: String,
    val imageList: List<String>,
    val detailList: List<String>
)