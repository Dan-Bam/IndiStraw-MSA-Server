package com.project.indistraw.account.adapter.output.persistence.common.converter

import com.project.indistraw.account.adapter.output.persistence.common.GenericConverter
import com.project.indistraw.account.adapter.output.persistence.entity.RefreshTokenEntity
import com.project.indistraw.account.domain.RefreshToken
import org.springframework.stereotype.Component

@Component
class RefreshTokenConverter: GenericConverter<RefreshToken, RefreshTokenEntity> {

    override infix fun toEntity(domain: RefreshToken): RefreshTokenEntity =
        domain.let {
            RefreshTokenEntity(
                refreshToken = it.refreshToken,
                accountIdx = it.accountIdx,
                expiredAt = it.expiredAt
            )
        }

    override infix fun toDomain(entity: RefreshTokenEntity?): RefreshToken? =
        entity?.let {
            RefreshToken(
                refreshToken = it.refreshToken,
                accountIdx = it.accountIdx,
                expiredAt = it.expiredAt
            )
        }

}