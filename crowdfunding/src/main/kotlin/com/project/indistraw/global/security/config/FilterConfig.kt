package com.project.indistraw.global.security.config

import com.project.indistraw.global.security.filter.AuthenticationFilter
import com.project.indistraw.global.security.token.TokenParser
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class FilterConfig(
    private val tokenParse: TokenParser
): SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {

    override fun configure(builder: HttpSecurity) {
        builder.addFilterBefore(AuthenticationFilter(tokenParse), UsernamePasswordAuthenticationFilter::class.java)
    }

}