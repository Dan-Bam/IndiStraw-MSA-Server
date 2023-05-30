package com.indistraw.account.application.port.output

interface PasswordEncodePort {

    fun passwordEncode(password: String): String
    fun isPasswordMatch(rawPassword: String, encodedPassword: String): Boolean

}