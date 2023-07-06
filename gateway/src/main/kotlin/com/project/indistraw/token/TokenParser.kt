package com.project.indistraw.token

import com.project.indistraw.token.common.properties.JwtProperties
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.stereotype.Component

@Component
class TokenParser(
    private val jwtProperties: JwtProperties
) {

    fun parseAccessToken(request: ServerHttpRequest): String? =
        request.headers[JwtProperties.TOKEN_HEADER]
            .let { it ?: return null }
            .let { it[0] ?: return null }
            .let { if (it.startsWith(JwtProperties.TOKEN_PREFIX)) it.replace(JwtProperties.TOKEN_PREFIX, "") else null }

    fun verifyExpiredAccessToken(token: String): Claims =
        Jwts.parserBuilder()
            .setSigningKey(jwtProperties.accessSecret)
            .build()
            .parseClaimsJws(token)
            .body

}
