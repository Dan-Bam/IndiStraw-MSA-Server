package com.project.indistraw.global.security.token

import com.project.indistraw.global.security.token.common.property.JwtProperties
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest

@Component
class TokenParser(
    private val jwtProperties: JwtProperties
) {

    fun parseAccessToken(request: HttpServletRequest): String? =
        request.getHeader(JwtProperties.TOKEN_HEADER)
            .let { it ?: return null }
            .let { if (it.startsWith(JwtProperties.TOKEN_PREFIX)) it.replace(JwtProperties.TOKEN_PREFIX, "") else null }

    fun getTokenSubject(accessToken: String): String =
        getTokenBody(accessToken).subject

    fun getAuthority(accessToken: String): String =
        getTokenBody(accessToken).get(JwtProperties.AUTHORITY, String::class.java)

    private fun getTokenBody(accessToken: String): Claims =
        Jwts.parserBuilder()
            .setSigningKey(jwtProperties.accessSecret)
            .build()
            .parseClaimsJws(accessToken)
            .body

}