package com.project.indistraw.global.security.config

import com.project.indistraw.global.security.handler.CustomAuthenticationEntryPoint
import com.project.indistraw.global.security.token.JwtParseAdapter
import com.project.indistraw.account.domain.Authority
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtParserAdapter: JwtParseAdapter
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain =
        http
            .cors()
            .and()
            .csrf().disable()
            .formLogin().disable()
            .httpBasic().disable()

            .authorizeRequests()
            // /auth
            .mvcMatchers(HttpMethod.POST, "/api/v1/auth/signup").permitAll()
            .mvcMatchers(HttpMethod.POST, "/api/v1/auth/signin").permitAll()
            .mvcMatchers(HttpMethod.HEAD, "/api/v1/auth/check/id/{id}").permitAll()
            .mvcMatchers(HttpMethod.HEAD, "/api/v1/auth/check/phone-number/{phone-number}").permitAll()
            .mvcMatchers(HttpMethod.POST, "/api/v1/auth/send/phone-number/{phoneNumber}").permitAll()
            .mvcMatchers(HttpMethod.PATCH, "/api/v1/auth").permitAll()

            // /account
            .mvcMatchers(HttpMethod.GET, "/api/v1/account/profile").hasAnyAuthority(Authority.ROLE_ACCOUNT.name, Authority.ROLE_ACCOUNT.name)

            // /health
            .mvcMatchers(HttpMethod.GET, "/").permitAll()

            .anyRequest().denyAll()
            .and()

            .exceptionHandling()
            .authenticationEntryPoint(CustomAuthenticationEntryPoint())
            .and()

            .apply(FilterConfig(jwtParserAdapter))
            .and()
            .build()

}