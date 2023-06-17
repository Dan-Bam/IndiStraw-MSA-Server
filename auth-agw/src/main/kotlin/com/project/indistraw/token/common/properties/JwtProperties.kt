package com.project.indistraw.token.common.properties

import io.jsonwebtoken.security.Keys
import org.springframework.boot.context.properties.ConfigurationProperties
import java.nio.charset.StandardCharsets
import java.security.Key

@ConfigurationProperties(prefix = "jwt")
class JwtProperties(
    accessSecret: String,
) {

    val accessSecret: Key = Keys.hmacShaKeyFor(accessSecret.toByteArray(StandardCharsets.UTF_8))

    companion object {
        const val TOKEN_PREFIX = "Bearer "
        const val TOKEN_HEADER = "Authorization"
    }

}