package com.project.indistraw.account.adapter.output.persistence

import com.project.indistraw.account.adapter.output.persistence.common.converter.RefreshTokenConverter
import com.project.indistraw.account.adapter.output.persistence.repository.RefreshTokenRepository
import com.project.indistraw.account.application.port.output.CommandRefreshTokenPort
import com.project.indistraw.account.domain.RefreshToken
import org.springframework.stereotype.Component

@Component
class CommandRefreshTokenPersistenceAdapter(
    private val refreshTokenRepository: RefreshTokenRepository,
    private val refreshTokenConverter: RefreshTokenConverter
): CommandRefreshTokenPort {

    override fun saveRefreshToken(refreshToken: RefreshToken): String {
        val refreshTokenEntity = refreshTokenConverter toEntity refreshToken
        return refreshTokenRepository.save(refreshTokenEntity).refreshToken
    }

}