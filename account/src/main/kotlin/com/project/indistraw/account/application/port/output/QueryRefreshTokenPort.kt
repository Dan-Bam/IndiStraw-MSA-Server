package com.project.indistraw.account.application.port.output

import com.project.indistraw.account.domain.RefreshToken

interface QueryRefreshTokenPort {

    fun findByRefreshTokenOrNull(refreshToken: String): RefreshToken?

}