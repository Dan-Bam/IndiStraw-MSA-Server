package com.project.indistraw.account.domain

data class AuthCode(
    val phoneNumber: String,
    val authCode: Int,
    val expiredAt: Long
)
