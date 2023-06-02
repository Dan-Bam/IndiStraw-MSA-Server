package com.project.indistraw.global.event

import java.util.UUID

data class SaveRefreshTokenEvent(
    val refreshToken: String,
    val accountIdx: UUID,
    val expiredAt: Int
)