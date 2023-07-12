package com.project.indistraw.global.event

import com.project.indistraw.account.domain.Authentication

data class DeleteAuthenticationEvent(
    val authentication: Authentication
)