package com.indistraw.account.adapter.output.security.config

import com.indistraw.account.adapter.output.security.jwt.JwtParseAdapter
import com.indistraw.account.adapter.output.security.filter.ExceptionHandlerFilter
import com.indistraw.account.adapter.output.security.filter.JwtRequestFilter
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class FilterConfig(
    private val jwtParseAdapter: JwtParseAdapter
): SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {

    override fun configure(builder: HttpSecurity) {
        builder.addFilterBefore(JwtRequestFilter(jwtParseAdapter), UsernamePasswordAuthenticationFilter::class.java)
        builder.addFilterBefore(ExceptionHandlerFilter(), JwtRequestFilter::class.java)
    }

}