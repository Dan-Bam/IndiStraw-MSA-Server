package com.project.indistraw.account.application.common.util

import com.project.indistraw.account.application.exception.AuthenticationNotFoundException
import com.project.indistraw.account.application.port.output.QueryAuthenticationPort
import com.project.indistraw.account.domain.Authentication
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class AuthenticationValidator(
    private val queryAuthenticationPort: QueryAuthenticationPort,
) {

    @Transactional(readOnly = true, rollbackFor = [Exception::class])
    fun verifyAuthenticationByPhoneNumber(phoneNumber: String): Authentication {
        val authentication = queryAuthenticationPort.findByPhoneNumberOrNull(phoneNumber)
            ?: throw AuthenticationNotFoundException()
        if (!authentication.isVerified) {
            throw AuthenticationNotFoundException()
        }
        return authentication
    }

}