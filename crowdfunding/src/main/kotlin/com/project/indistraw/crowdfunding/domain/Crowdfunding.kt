package com.project.indistraw.crowdfunding.domain

import com.project.indistraw.crowdfunding.application.common.annotation.AggregateRoot
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@AggregateRoot
data class Crowdfunding(
    val idx: Long,
    val writerIdx: UUID,
    val title: String,
    val description: String,
    val amount: Amount,
    val directorAccount: DirectorAccount,
    val createdAt: LocalDateTime,
    val endDate: LocalDate,
    var viewCount: Long,
    var statusType: StatusType,
    val thumbnailUrl: String,
    val imageList: List<String>,
    val detailList: List<String>
) {

    fun increaseViewCount(): Crowdfunding {
        this.viewCount = viewCount.inc()
        return this
    }

    fun updateStatus(statusType: StatusType): Crowdfunding {
        this.statusType = statusType
        return this
    }

}