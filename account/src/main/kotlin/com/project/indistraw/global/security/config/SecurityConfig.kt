package com.project.indistraw.global.security.config

import com.project.indistraw.global.security.handler.CustomAuthenticationEntryPoint
import com.project.indistraw.global.security.token.TokenParseAdapter
import com.project.indistraw.account.domain.Authority
import com.project.indistraw.global.security.handler.CustomAccessDeniedHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtParserAdapter: TokenParseAdapter
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
            .mvcMatchers(HttpMethod.PATCH, "/api/v1/auth/reissue").permitAll()
            .mvcMatchers(HttpMethod.DELETE, "/api/v1/auth/logout").permitAll()
            .mvcMatchers(HttpMethod.HEAD, "/api/v1/auth/check/id/{id}").permitAll()
            .mvcMatchers(HttpMethod.HEAD, "/api/v1/auth/check/phone-number/{phone-number}/type/{type}").permitAll()
            .mvcMatchers(HttpMethod.POST, "/api/v1/auth/send/phone-number/{phoneNumber}").permitAll()
            .mvcMatchers(HttpMethod.GET, "/api/v1/auth/auth-code/{authCode}/phone-number/{phoneNumber}").permitAll()

            // qr-code
            .mvcMatchers(HttpMethod.POST, "/api/v1/qr-code").permitAll()
            .mvcMatchers(HttpMethod.POST, "/api/v1/check/uuid/{uuid}").permitAll()
            .mvcMatchers(HttpMethod.PATCH, "/api/v1/ping/{uuid}").permitAll()

            // /account
            .mvcMatchers(HttpMethod.GET, "/api/v1/account/phone-number/{phoneNumber}").permitAll()
            .mvcMatchers(HttpMethod.PATCH, "/api/v1/account/password").permitAll()
            .mvcMatchers(HttpMethod.PATCH, "/api/v1/account/phone-number/{phoneNumber}").hasAnyAuthority(Authority.ROLE_ACCOUNT.name, Authority.ROLE_ADMIN.name)
            .mvcMatchers(HttpMethod.PATCH, "/api/v1/account/address").hasAnyAuthority(Authority.ROLE_ACCOUNT.name, Authority.ROLE_ADMIN.name)
            .mvcMatchers(HttpMethod.PATCH, "/api/v1/account/info").hasAnyAuthority(Authority.ROLE_ACCOUNT.name, Authority.ROLE_ADMIN.name)
            .mvcMatchers(HttpMethod.GET, "/api/v1/account/info").hasAnyAuthority(Authority.ROLE_ACCOUNT.name, Authority.ROLE_ADMIN.name)
            .mvcMatchers(HttpMethod.DELETE, "/api/v1/account").hasAnyAuthority(Authority.ROLE_ACCOUNT.name, Authority.ROLE_ADMIN.name)

            // /health
            .mvcMatchers(HttpMethod.GET, "/").permitAll()

            .anyRequest().authenticated()
            .and()

            .exceptionHandling()
            .accessDeniedHandler(CustomAccessDeniedHandler())
            .and()
            .httpBasic()
            .authenticationEntryPoint(CustomAuthenticationEntryPoint())
            .and()

            .apply(FilterConfig(jwtParserAdapter))
            .and()
            .build()

}