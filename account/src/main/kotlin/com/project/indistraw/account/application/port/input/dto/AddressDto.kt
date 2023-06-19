package com.project.indistraw.account.application.port.input.dto

data class AddressDto(
    val zipcode: String?,
    val streetAddress: String?,
    val detailAddress: String?
)