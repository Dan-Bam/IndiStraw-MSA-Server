package com.project.indistraw.account.adapter.output.persistence.common.converter

import com.project.indistraw.account.adapter.output.persistence.entity.AuthCodeEntity
import com.project.indistraw.account.domain.AuthCode
import org.springframework.stereotype.Component

@Component
class AuthCodeConverter {

    infix fun toEntity(domain: AuthCode): AuthCodeEntity =
        domain.let {
            AuthCodeEntity(
                phoneNumber = it.phoneNumber,
                authCode = it.authCode,
                expiredAt = it.expiredAt
            )
        }

    infix fun toDomain(entity: AuthCodeEntity?): AuthCode? =
        entity?.let {
            AuthCode(
                phoneNumber = it.phoneNumber,
                authCode = it.authCode,
                expiredAt = it.expiredAt
            )
        }

}