package com.project.indistraw.account.application.port.input.dto

data class UpdateAccountProfileDto(
    val name: String,
    val phoneNumber: String,
    val address: String,
    val profileUrl: String?
)