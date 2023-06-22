package com.project.indistraw.crowdfunding.domain

import com.project.indistraw.crowdfunding.application.common.annotation.Aggregate
import java.time.LocalDate
import java.time.LocalDateTime

@Aggregate
data class Date(
    val createdDate: LocalDateTime,
    val endDate: LocalDate
)