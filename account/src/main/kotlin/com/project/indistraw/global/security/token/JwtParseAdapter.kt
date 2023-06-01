package com.project.indistraw.global.security.token

import com.project.indistraw.global.security.token.common.exception.InvalidTokenTypeException
import com.project.indistraw.global.security.token.common.properties.JwtProperties
import com.project.indistraw.global.security.principal.AccountDetailsService
import com.project.indistraw.global.security.principal.AdminDetailsService
import com.project.indistraw.account.application.port.output.JwtParsePort
import com.project.indistraw.account.domain.Authority
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.security.Key
import javax.servlet.http.HttpServletRequest

@Component
class JwtParseAdapter(
    private val accountDetailsService: AccountDetailsService,
    private val adminDetailsService: AdminDetailsService,
    private val jwtProperties: JwtProperties
): JwtParsePort {

    override fun parseAccessToken(request: HttpServletRequest): String? =
        request.getHeader(JwtProperties.TOKEN_HEADER)
            .let { it ?: return null }
            .let { if (it.startsWith(JwtProperties.TOKEN_PREFIX)) it.replace(JwtProperties.TOKEN_PREFIX, "") else null }

    override fun parseRefreshTokenToken(refreshToken: String): String? =
        if (refreshToken.startsWith(JwtProperties.TOKEN_PREFIX)) refreshToken.replace(JwtProperties.TOKEN_PREFIX, "") else null

    override fun authentication(token: String): Authentication =
        getAuthority(getTokenBody(token, jwtProperties.accessSecret))
            .let { UsernamePasswordAuthenticationToken(it, "", it.authorities) }

    private fun getTokenBody(token: String, secret: Key): Claims =
        Jwts.parserBuilder()
            .setSigningKey(secret)
            .build()
            .parseClaimsJws(token)
            .body

    private fun getAuthority(body: Claims): UserDetails =
        when (body.get(JwtProperties.AUTHORITY, String::class.java)) {
            Authority.ROLE_ACCOUNT.name -> accountDetailsService.loadUserByUsername(body.subject)
            Authority.ROLE_ADMIN.name -> adminDetailsService.loadUserByUsername(body.subject)
            else -> throw InvalidTokenTypeException()
        }

}
