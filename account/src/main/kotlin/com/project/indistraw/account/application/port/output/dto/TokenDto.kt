package com.project.indistraw.account.application.port.output.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class TokenDto(
    val accessToken: String,
    val refreshToken: String,
    val accessTokenExpiredAt: Long,
    val refreshTokenExpiredAt: Long
) {

    override fun toString(): String {
        return "{" +
                "\"accessToken\":" + "\"" + this.accessToken + "\"," +
                "\"refreshToken\":" + "\"" + this.refreshToken + "\"," +
                "\"accessTokenExpiredAt\":" + LocalDateTime.now().plusSeconds(this.accessTokenExpiredAt).withNano(0) + "," +
                "\"refreshTokenExpiredAt\":" + LocalDateTime.now().plusSeconds(this.refreshTokenExpiredAt).withNano(0) + "}"
    }

}
