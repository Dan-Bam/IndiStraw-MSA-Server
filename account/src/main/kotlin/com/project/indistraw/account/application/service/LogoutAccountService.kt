package com.project.indistraw.account.application.service

import com.project.indistraw.account.application.common.annotation.ServiceWithTransaction
import com.project.indistraw.account.application.port.input.LogoutAccountUseCase
import com.project.indistraw.account.application.port.output.CommandRefreshTokenPort
import com.project.indistraw.account.application.port.output.QueryRefreshTokenPort
import com.project.indistraw.global.security.token.common.exception.ExpiredRefreshTokenException

@ServiceWithTransaction
class LogoutAccountService(
    private val commandRefreshTokenPort: CommandRefreshTokenPort,
    private val queryRefreshTokenPort: QueryRefreshTokenPort,
): LogoutAccountUseCase {

    override fun execute(refreshToken: String) {
        val refreshTokenDomain = queryRefreshTokenPort.findByRefreshTokenOrNull(refreshToken)
            ?: throw ExpiredRefreshTokenException()

        commandRefreshTokenPort.deleteRefreshToken(refreshTokenDomain)
    }

}