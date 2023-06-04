package com.project.indistraw.account.adapter.output.persistence

import com.project.indistraw.account.adapter.output.persistence.common.converter.AuthenticationConverter
import com.project.indistraw.account.adapter.output.persistence.repository.AuthenticationRepository
import com.project.indistraw.account.application.port.output.CommandAuthenticationPort
import com.project.indistraw.account.domain.Authentication
import org.springframework.stereotype.Component

@Component
class CommandAuthenticationPersistenceAdapter(
    private val authenticationRepository: AuthenticationRepository,
    private val authenticationConverter: AuthenticationConverter
): CommandAuthenticationPort {

    override fun saveAuthentication(authentication: Authentication) {
        val authenticationEntity = authenticationConverter toEntity authentication
        authenticationRepository.save(authenticationEntity)
    }

}