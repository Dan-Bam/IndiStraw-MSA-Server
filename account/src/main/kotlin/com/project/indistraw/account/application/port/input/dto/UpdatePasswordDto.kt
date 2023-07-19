package com.project.indistraw.account.application.port.input.dto

data class UpdatePasswordDto(
    val phoneNumber: String,
    val newPassword: String
)