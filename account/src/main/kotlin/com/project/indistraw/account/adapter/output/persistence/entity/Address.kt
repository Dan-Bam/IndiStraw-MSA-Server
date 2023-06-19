package com.project.indistraw.account.adapter.output.persistence.entity

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Address(
    @Column(nullable = true)
    val zipcode: String,

    @Column(nullable = true)
    val streetAddress: String,

    @Column(nullable = true)
    val detailAddress: String
)