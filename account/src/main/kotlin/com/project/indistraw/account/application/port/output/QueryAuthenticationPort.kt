package com.project.indistraw.account.application.port.output

import com.project.indistraw.account.domain.Authentication

interface QueryAuthenticationPort {

    fun findByPhoneNumberOrNull(phoneNumber: String): Authentication?
    fun existsByPhoneNumber(phoneNumber: String): Boolean

}