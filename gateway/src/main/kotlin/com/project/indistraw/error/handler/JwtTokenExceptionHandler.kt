package com.project.indistraw.error.handler

import com.project.indistraw.token.common.exception.InvalidTokenException
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.JwtException
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class JwtTokenExceptionHandler: ErrorWebExceptionHandler {

    override fun handle(exchange: ServerWebExchange, ex: Throwable): Mono<Void> {
        return when (ex::class.java) {
            InvalidTokenException::class.java -> exceptionToResponse(exchange, HttpStatus.UNAUTHORIZED)
            ExpiredJwtException::class.java -> exceptionToResponse(exchange, HttpStatus.UNAUTHORIZED)
            JwtException::class.java -> exceptionToResponse(exchange, HttpStatus.UNAUTHORIZED)
            else -> exceptionToResponse(exchange, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    private fun exceptionToResponse(exchange: ServerWebExchange, httpStatus: HttpStatus): Mono<Void> {
        exchange.response.statusCode = httpStatus
        return exchange.response.setComplete()
    }

}