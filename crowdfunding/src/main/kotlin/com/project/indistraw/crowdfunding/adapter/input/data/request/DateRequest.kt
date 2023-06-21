package com.project.indistraw.crowdfunding.adapter.input.data.request

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import javax.validation.constraints.NotNull

data class DateRequest(
    @field:NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    val endDate: LocalDate
)
