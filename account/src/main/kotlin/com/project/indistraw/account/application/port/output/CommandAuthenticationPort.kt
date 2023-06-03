package com.project.indistraw.account.application.port.output

import com.project.indistraw.account.domain.Authentication

interface CommandAuthenticationPort {

    fun saveAuthentication(authentication: Authentication)

}