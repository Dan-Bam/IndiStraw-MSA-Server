package com.indistraw.account.application.port.output

import org.springframework.security.core.Authentication
import javax.servlet.http.HttpServletRequest

interface JwtParsePort {

    fun parseAccessToken(request: HttpServletRequest): String?
    fun parseRefreshTokenToken(refreshToken: String): String?
    fun authentication(token: String): Authentication

}