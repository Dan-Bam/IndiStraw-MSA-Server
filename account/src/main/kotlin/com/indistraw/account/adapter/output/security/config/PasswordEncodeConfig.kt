package com.indistraw.account.adapter.output.security.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class PasswordEncodeConfig {

    @Bean
    fun passwordEncoder(): PasswordEncoder? = BCryptPasswordEncoder()

}