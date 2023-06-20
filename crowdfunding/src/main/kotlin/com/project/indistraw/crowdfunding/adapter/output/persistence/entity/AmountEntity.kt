package com.project.indistraw.crowdfunding.adapter.output.persistence.entity

import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class AmountEntity(
    @Column(nullable = false)
    val totalAmount: BigDecimal,

    @Column(nullable = false)
    val targetAmount: BigDecimal
)