package com.project.indistraw.crowdfunding.adapter.output.persistence.entity

import org.springframework.data.annotation.CreatedDate
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class DateEntity(
    @CreatedDate
    @Column(nullable = false, updatable = false)
    val createdDate: LocalDateTime,

    @Column(nullable = false)
    val endDate: LocalDate
)