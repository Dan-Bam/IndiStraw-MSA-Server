package com.project.indistraw.account.application.port.output

import com.project.indistraw.account.domain.AuthCode

interface CommandAuthCodePort {

    fun saveAuthCode(authCode: AuthCode): Int

}