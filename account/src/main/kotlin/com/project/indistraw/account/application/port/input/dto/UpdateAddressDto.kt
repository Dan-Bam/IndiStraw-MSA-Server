package com.project.indistraw.account.application.port.input.dto

data class UpdateAddressDto(
    val zipcode: String,
    val streetAddress: String,
    val detailAddress: String
)