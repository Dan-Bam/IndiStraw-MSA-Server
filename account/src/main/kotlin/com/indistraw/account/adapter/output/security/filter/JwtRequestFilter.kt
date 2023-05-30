package com.indistraw.account.adapter.output.security.filter

import com.indistraw.account.adapter.output.security.jwt.JwtParseAdapter
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtRequestFilter(
    private val jwtParseAdapter: JwtParseAdapter
): OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        val accessToken = jwtParseAdapter.parseAccessToken(request)

        if (!accessToken.isNullOrBlank()) {
            val authentication = jwtParseAdapter.authentication(accessToken)
            SecurityContextHolder.clearContext()
            SecurityContextHolder.getContext().authentication = authentication
        }

        filterChain.doFilter(request, response)

    }

}