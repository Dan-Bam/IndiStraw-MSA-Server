package com.project.indistraw.crowdfunding.domain

import com.project.indistraw.crowdfunding.application.common.annotation.Aggregate
import com.project.indistraw.crowdfunding.application.common.annotation.AggregateRoot
import java.util.*

@AggregateRoot
data class Account(
    val accountIdx: UUID,
    var name: String,
    var phoneNumber: String,
    var address: Address?,
    var profileUrl: String?
) {

    @Aggregate
    data class Address(
        val zipcode: String?,
        val streetAddress: String?,
        val detailAddress: String?
    )

    fun updateInfo(name: String, profileUrl: String?): Account {
        this.name = name
        this.profileUrl = profileUrl
        return this
    }

    fun updateAddress(address: Address): Account {
        this.address = address
        return this
    }

    fun updatePhoneNumber(phoneNumber: String): Account {
        this.phoneNumber = phoneNumber
        return this
    }

}