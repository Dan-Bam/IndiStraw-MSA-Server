package com.project.indistraw.account.adapter.output.persistence.common.converter

import com.project.indistraw.account.adapter.output.persistence.common.GenericConverter
import com.project.indistraw.account.adapter.output.persistence.entity.AuthCodeEntity
import com.project.indistraw.account.domain.AuthCode
import org.springframework.stereotype.Component

@Component
class AuthCodeConverter: GenericConverter<AuthCode, AuthCodeEntity> {

    override infix fun toEntity(domain: AuthCode): AuthCodeEntity =
        domain.let {
            AuthCodeEntity(
                phoneNumber = it.phoneNumber,
                authCode = it.authCode,
                expiredAt = it.expiredAt
            )
        }

    override infix fun toDomain(entity: AuthCodeEntity?): AuthCode? =
        entity?.let {
            AuthCode(
                phoneNumber = it.phoneNumber,
                authCode = it.authCode,
                expiredAt = it.expiredAt
            )
        }

}