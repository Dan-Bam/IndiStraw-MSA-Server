package com.project.indistraw.account.adapter.input.request

import javax.validation.constraints.NotNull

data class UpdateAddressRequest(
    @field:NotNull
    val zipcode: String,

    @field:NotNull
    val streetAddress: String,

    @field:NotNull
    val detailAddress: String
)
