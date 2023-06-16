package com.project.indistraw.account.application.port.output

import com.project.indistraw.account.domain.AuthCode

interface QueryAuthCodePort {

    fun findByPhoneNumberOrNull(phoneNumber: String): AuthCode?

}