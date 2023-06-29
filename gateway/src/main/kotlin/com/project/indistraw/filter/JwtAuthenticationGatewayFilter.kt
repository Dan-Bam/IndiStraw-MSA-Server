package com.project.indistraw.filter

import com.project.indistraw.token.TokenParser
import com.project.indistraw.token.common.exception.InvalidTokenException
import mu.KotlinLogging
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.stereotype.Component

private val log = KotlinLogging.logger {  }

@Component
class JwtAuthenticationGatewayFilter(
    private val tokenParser: TokenParser
): AbstractGatewayFilterFactory<JwtAuthenticationGatewayFilter.Config>(Config::class.java) {

    class Config {}

    override fun apply(config: Config?): GatewayFilter {
        return GatewayFilter { exchange, chain ->
            log.info("JwtAuthenticationGatewayFilter is active")
            val accessToken = tokenParser.parseAccessToken(exchange.request)
                ?: throw InvalidTokenException()

            tokenParser.verifyExpiredAccessToken(accessToken)

            chain.filter(exchange)
        }
    }

}