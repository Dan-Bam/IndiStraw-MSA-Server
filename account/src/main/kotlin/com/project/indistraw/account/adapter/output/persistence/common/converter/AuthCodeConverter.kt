package com.project.indistraw.account.adapter.output.persistence.common.converter

import com.project.indistraw.account.adapter.output.persistence.entity.AuthCodeEntity
import com.project.indistraw.account.domain.AuthCode
import org.springframework.stereotype.Component

@Component
class AuthCodeConverter {

    infix fun toEntity(domain: AuthCode): AuthCodeEntity =
        domain.let {
            AuthCodeEntity(
                authCode = it.authCode,
                phoneNumber = it.phoneNumber
            )
        }

    infix fun toDomain(entity: AuthCode?): AuthCode? =
        entity?.let {
            AuthCode(
                authCode = it.authCode,
                phoneNumber = it.phoneNumber
            )
        }

}