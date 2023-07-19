package com.project.indistraw.global.security.token.common.property

import io.jsonwebtoken.security.Keys
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import java.nio.charset.StandardCharsets
import java.security.Key

@ConstructorBinding
@ConfigurationProperties(prefix = "jwt")
class JwtProperties(
    accessSecret: String
) {

    val accessSecret: Key = Keys.hmacShaKeyFor(accessSecret.toByteArray(StandardCharsets.UTF_8))

    companion object {
        const val TOKEN_PREFIX = "Bearer "
        const val TOKEN_HEADER = "Authorization"
        const val AUTHORITY = "authority"
    }

}