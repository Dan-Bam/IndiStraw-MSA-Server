package com.project.indistraw.crowdfunding.domain

import java.time.LocalDate
import java.time.LocalDateTime

data class Date(
    val createdDate: LocalDateTime,
    val endDate: LocalDate
)