package com.project.indistraw.account.application.port.output.dto

import java.time.LocalDateTime

data class TokenDto(
    val accessToken: String,
    val refreshToken: String,
    val accessTokenExpiredAt: LocalDateTime,
    val refreshTokenExpiredAt: LocalDateTime
)
