package com.project.indistraw.account.application.port.output.dto

data class TokenDto(
    val accessToken: String,
    val refreshToken: String,
    val accessTokenExpiredAt: Int,
    val refreshTokenExpiredAt: Int
)
