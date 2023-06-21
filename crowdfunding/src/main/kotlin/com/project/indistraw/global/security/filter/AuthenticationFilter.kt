package com.project.indistraw.global.security.filter

import com.project.indistraw.global.security.token.TokenParser
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthenticationFilter(
    private val tokenParser: TokenParser
): OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val accessToken = tokenParser.parseAccessToken(request)

        if (!accessToken.isNullOrBlank()) {
            val accountIdx = tokenParser.getTokenSubject(accessToken)
            val authority = mutableListOf(SimpleGrantedAuthority(tokenParser.getAuthority(accessToken)))

            val userDetails = User(accountIdx, "", authority)
            val authentication = UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
            SecurityContextHolder.getContext().authentication = authentication
            filterChain.doFilter(request, response)
        }
    }

}