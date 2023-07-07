package com.project.indistraw.global.event.authentication

import com.project.indistraw.account.domain.Authentication

data class DeleteAuthenticationEvent(
    val authentication: Authentication
)