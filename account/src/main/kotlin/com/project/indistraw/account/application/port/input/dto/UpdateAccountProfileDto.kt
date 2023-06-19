package com.project.indistraw.account.application.port.input.dto

data class UpdateAccountProfileDto(
    val name: String,
    val phoneNumber: String,
    val address: AddressDto?,
    val profileUrl: String?
)