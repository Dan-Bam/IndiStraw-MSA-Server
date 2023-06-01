package com.project.indistraw.global.security

import com.project.indistraw.account.application.port.output.PasswordEncodePort
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class PasswordEncodeAdapter(
    private val passwordEncoder: PasswordEncoder
): PasswordEncodePort {

    override fun passwordEncode(password: String): String =
        passwordEncoder.encode(password)

    override fun isPasswordMatch(rawPassword: String, encodedPassword: String): Boolean =
        passwordEncoder.matches(rawPassword, encodedPassword)

}