package com.project.indistraw.global.security.config

import com.project.indistraw.global.security.handler.CustomAuthenticationEntryPoint
import com.project.indistraw.global.security.token.TokenParser
import com.project.indistraw.global.security.token.common.emums.Authority
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val tokenParser: TokenParser
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
            // /crowdfunding
            .mvcMatchers(HttpMethod.POST, "/api/v1/crowdfunding").hasAnyAuthority(Authority.ROLE_ACCOUNT.name, Authority.ROLE_ADMIN.name)
            .mvcMatchers(HttpMethod.GET, "/api/v1/crowdfunding/{idx}").hasAnyAuthority(Authority.ROLE_ACCOUNT.name, Authority.ROLE_ADMIN.name)
            .mvcMatchers(HttpMethod.GET, "/api/v1/crowdfunding").hasAnyAuthority(Authority.ROLE_ACCOUNT.name, Authority.ROLE_ADMIN.name)
            .mvcMatchers(HttpMethod.PATCH, "/api/v1/crowdfunding/{idx}").hasAnyAuthority(Authority.ROLE_ACCOUNT.name, Authority.ROLE_ADMIN.name)
            .mvcMatchers(HttpMethod.DELETE, "/api/v1/crowdfunding/{idx}").hasAnyAuthority(Authority.ROLE_ACCOUNT.name, Authority.ROLE_ADMIN.name)
            .mvcMatchers(HttpMethod.HEAD, "/api/v1/crowdfunding/{idx}/attend").hasAnyAuthority(Authority.ROLE_ACCOUNT.name, Authority.ROLE_ADMIN.name)
            .mvcMatchers(HttpMethod.GET, "/api/v1/crowdfunding/{idx}/users").hasAnyAuthority(Authority.ROLE_ACCOUNT.name, Authority.ROLE_ADMIN.name)

            // /health
            .mvcMatchers(HttpMethod.GET, "/").permitAll()

            .anyRequest().denyAll()
            .and()

            .exceptionHandling()
            .authenticationEntryPoint(CustomAuthenticationEntryPoint())
            .and()

            .apply(FilterConfig(tokenParser))
            .and()
            .build()

}