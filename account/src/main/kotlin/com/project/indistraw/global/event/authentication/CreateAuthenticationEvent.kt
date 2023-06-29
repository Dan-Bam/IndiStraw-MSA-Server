package com.project.indistraw.global.event.authentication

import com.project.indistraw.account.domain.Authentication

data class CreateAuthenticationEvent(
    val authentication: Authentication
)
