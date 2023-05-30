package com.indistraw.account.domain

import java.util.UUID

data class RefreshToken(
    val refreshToken: String,
    val accountIdx: UUID,
    val expiredAt: Int
)