package com.project.indistraw.account.adapter.output.persistence

import com.project.indistraw.account.adapter.output.persistence.common.converter.RefreshTokenConverter
import com.project.indistraw.account.adapter.output.persistence.repository.RefreshTokenRepository
import com.project.indistraw.account.application.port.output.QueryRefreshTokenPort
import com.project.indistraw.account.domain.RefreshToken
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class QueryRefreshTokenPersistenceAdapter(
    private val refreshTokenRepository: RefreshTokenRepository,
    private val refreshTokenConverter: RefreshTokenConverter
): QueryRefreshTokenPort {

    override fun findByRefreshTokenOrNull(refreshToken: String): RefreshToken? {
        val refreshTokenEntity = refreshTokenRepository.findByIdOrNull(refreshToken)
        return refreshTokenConverter toDomain refreshTokenEntity
    }

}