package com.project.indistraw.account.adapter.output.persistence.entity

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Address(
    @Column(nullable = false)
    val zipcode: String,

    @Column(nullable = false)
    val streetAddress: String,

    @Column(nullable = false)
    val detailAddress: String
)