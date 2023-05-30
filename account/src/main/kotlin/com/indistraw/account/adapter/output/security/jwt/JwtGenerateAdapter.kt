package com.indistraw.account.adapter.output.security.jwt

import com.indistraw.account.adapter.output.security.jwt.common.properties.JwtExpTimeProperties
import com.indistraw.account.adapter.output.security.jwt.common.properties.JwtProperties
import com.indistraw.account.application.port.output.JwtGeneratePort
import com.indistraw.account.application.port.output.dto.TokenDto
import com.indistraw.account.domain.Authority
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.*

@Component
class JwtGenerateAdapter(
    private val jwtProperties: JwtProperties,
    private val jwtExpTimeProperties: JwtExpTimeProperties
): JwtGeneratePort {

    override fun generateToken(accountIdx: UUID, authority: Authority): TokenDto =
        TokenDto(
            accessToken = generateAccessToken(accountIdx, authority),
            refreshToken = generateRefreshToken(accountIdx),
            accessTokenExpiredAt = LocalDateTime.now().plusSeconds(jwtExpTimeProperties.accessExp.toLong()),
            refreshTokenExpiredAt = LocalDateTime.now().plusSeconds(jwtExpTimeProperties.refreshExp.toLong()),
        )

    private fun generateAccessToken(accountIdx: UUID, authority: Authority): String =
        Jwts.builder()
            .signWith(jwtProperties.accessSecret, SignatureAlgorithm.HS256)
            .setSubject(accountIdx.toString())
            .claim(JwtProperties.TOKEN_TYPE, JwtProperties.ACCESS)
            .claim(JwtProperties.AUTHORITY, authority)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + jwtExpTimeProperties.accessExp * 1000))
            .compact()

    private fun generateRefreshToken(accountIdx: UUID): String =
        Jwts.builder()
            .signWith(jwtProperties.refreshSecret, SignatureAlgorithm.HS256)
            .setSubject(accountIdx.toString())
            .claim(JwtProperties.TOKEN_TYPE, JwtProperties.REFRESH)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + jwtExpTimeProperties.refreshExp * 1000))
            .compact()

}