package com.project.indistraw.global.security.config

import com.project.indistraw.account.application.port.output.TokenParsePort
import com.project.indistraw.global.security.filter.ExceptionHandlerFilter
import com.project.indistraw.global.security.filter.JwtRequestFilter
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class FilterConfig(
    private val tokenParsePort: TokenParsePort
): SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {

    override fun configure(builder: HttpSecurity) {
        builder.addFilterBefore(JwtRequestFilter(tokenParsePort), UsernamePasswordAuthenticationFilter::class.java)
        builder.addFilterBefore(ExceptionHandlerFilter(), JwtRequestFilter::class.java)
    }

}