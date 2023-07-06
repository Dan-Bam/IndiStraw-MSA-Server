package com.project.indistraw.account.application.port.output

import io.jsonwebtoken.Claims
import org.springframework.security.core.Authentication
import javax.servlet.http.HttpServletRequest

interface TokenParsePort {

    fun parseAccessToken(request: HttpServletRequest): String?
    fun parseRefreshTokenToken(refreshToken: String): String?
    fun authentication(token: String): Authentication
    fun getAccessTokenClaim(accessToken: String): Claims

}