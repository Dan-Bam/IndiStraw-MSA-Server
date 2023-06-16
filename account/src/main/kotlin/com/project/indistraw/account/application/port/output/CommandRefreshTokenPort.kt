package com.project.indistraw.account.application.port.output

import com.project.indistraw.account.domain.RefreshToken

interface CommandRefreshTokenPort {

    fun saveRefreshToken(refreshToken: RefreshToken): String
    fun deleteRefreshToken(refreshToken: RefreshToken)

}