package com.project.indistraw.account.application.port.input.dto

data class SignUpDto(
    val id: String,
    val password: String,
    val name: String,
    val phoneNumber: String,
    val address: AddressDto,
    val profileUrl: String?
)