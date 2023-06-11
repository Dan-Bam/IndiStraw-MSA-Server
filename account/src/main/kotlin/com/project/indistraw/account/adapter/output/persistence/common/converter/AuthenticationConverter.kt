package com.project.indistraw.account.adapter.output.persistence.common.converter

import com.project.indistraw.account.adapter.output.persistence.entity.AuthenticationEntity
import com.project.indistraw.account.domain.Authentication
import org.springframework.stereotype.Component

@Component
class AuthenticationConverter {

    infix fun toEntity(domain: Authentication): AuthenticationEntity =
        domain.let {
            AuthenticationEntity(
                phoneNumber = it.phoneNumber,
                authCodeCount = it.authCodeCount,
                authenticationCount = it.authenticationCount,
                isVerified = it.isVerified,
                expiredAt = it.expiredAt
            )
        }

    infix fun toDomain(entity: AuthenticationEntity?): Authentication? =
        entity?.let {
            Authentication(
                phoneNumber = it.phoneNumber,
                authCodeCount = it.authCodeCount,
                authenticationCount = it.authenticationCount,
                isVerified = it.isVerified,
                expiredAt = it.expiredAt
            )
        }

}