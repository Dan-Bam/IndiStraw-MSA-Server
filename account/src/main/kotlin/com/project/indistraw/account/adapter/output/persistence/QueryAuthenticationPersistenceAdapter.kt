package com.project.indistraw.account.adapter.output.persistence

import com.project.indistraw.account.adapter.output.persistence.common.converter.AuthenticationConverter
import com.project.indistraw.account.adapter.output.persistence.repository.AuthenticationRepository
import com.project.indistraw.account.application.port.output.QueryAuthenticationPort
import com.project.indistraw.account.domain.Authentication
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class QueryAuthenticationPersistenceAdapter(
    private val authenticationRepository: AuthenticationRepository,
    private val authenticationConverter: AuthenticationConverter
): QueryAuthenticationPort {

    override fun findByPhoneNumberOrNull(phoneNumber: String): Authentication? {
        val authenticationEntity = authenticationRepository.findByIdOrNull(phoneNumber)
        return authenticationConverter toDomain authenticationEntity
    }

}