package com.project.indistraw.service

import com.project.indistraw.account.application.port.output.CommandRefreshTokenPort
import com.project.indistraw.account.application.port.output.QueryRefreshTokenPort
import com.project.indistraw.account.application.port.output.TokenParsePort
import com.project.indistraw.account.application.service.LogoutAccountService
import com.project.indistraw.account.domain.RefreshToken
import com.project.indistraw.common.AnyValueObjectGenerator
import com.project.indistraw.global.security.token.common.exception.ExpiredRefreshTokenException
import com.project.indistraw.global.security.token.common.exception.InvalidTokenTypeException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class LogoutAccountServiceTest: BehaviorSpec({
    val commandRefreshTokenPort = mockk<CommandRefreshTokenPort>()
    val tokenParsePort = mockk<TokenParsePort>()
    val queryRefreshTokenPort = mockk<QueryRefreshTokenPort>()
    val logoutAccountService = LogoutAccountService(commandRefreshTokenPort, tokenParsePort, queryRefreshTokenPort)

    Given("refreshToken이 주어질때") {
        val refreshToken = "Bearer test refreshToken"
        val parsedRefreshToken = "test refreshToken"
        val refreshTokenDomain = AnyValueObjectGenerator.anyValueObject<RefreshToken>("refreshToken" to refreshToken)

        every { tokenParsePort.parseRefreshTokenToken(refreshToken) } returns parsedRefreshToken
        every { queryRefreshTokenPort.findByRefreshTokenOrNull(parsedRefreshToken) } returns refreshTokenDomain
        every { commandRefreshTokenPort.deleteRefreshToken(refreshTokenDomain) } returns Unit

        When("로그아웃 요청을 하면") {
            logoutAccountService.execute(refreshToken)

            Then("refreshToken hash가 삭제가 되어야 한다.") {
                verify { commandRefreshTokenPort.deleteRefreshToken(refreshTokenDomain) }
            }
        }

        When("유효하지 않은 토큰 타입으로 refreshToken으로 요청하면") {
            every { tokenParsePort.parseRefreshTokenToken(refreshToken) } returns null

            shouldThrow<InvalidTokenTypeException> {
                logoutAccountService.execute(refreshToken)
            }
        }

        When("만료되서 삭제된 refreshToken으로 요청하면") {
            every { tokenParsePort.parseRefreshTokenToken(refreshToken) } returns parsedRefreshToken
            every { queryRefreshTokenPort.findByRefreshTokenOrNull(parsedRefreshToken) } returns null

            shouldThrow<ExpiredRefreshTokenException> {
                logoutAccountService.execute(refreshToken)
            }
        }
    }

})