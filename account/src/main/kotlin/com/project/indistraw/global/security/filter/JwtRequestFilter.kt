package com.project.indistraw.global.security.filter

import com.project.indistraw.account.application.port.output.TokenParsePort
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtRequestFilter(
    private val tokenParsePort: TokenParsePort
): OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        val accessToken = tokenParsePort.parseAccessToken(request)

        if (!accessToken.isNullOrBlank()) {
            val authentication = tokenParsePort.authentication(accessToken)
            SecurityContextHolder.clearContext()
            SecurityContextHolder.getContext().authentication = authentication
        }

        filterChain.doFilter(request, response)

    }

}