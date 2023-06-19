package com.project.indistraw.service

import com.project.indistraw.account.application.exception.AccountNotFoundException
import com.project.indistraw.account.application.port.output.QueryAccountPort
import com.project.indistraw.account.application.port.output.QueryRefreshTokenPort
import com.project.indistraw.account.application.port.output.TokenGeneratePort
import com.project.indistraw.account.application.port.output.TokenParsePort
import com.project.indistraw.account.application.port.output.dto.TokenDto
import com.project.indistraw.account.application.service.ReissueTokenService
import com.project.indistraw.account.domain.Account
import com.project.indistraw.account.domain.RefreshToken
import com.project.indistraw.common.AnyValueObjectGenerator
import com.project.indistraw.global.event.SaveRefreshTokenEvent
import com.project.indistraw.global.security.token.common.exception.ExpiredRefreshTokenException
import com.project.indistraw.global.security.token.common.exception.InvalidTokenException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.context.ApplicationEventPublisher

class ReissueTokenServiceTest: BehaviorSpec({
    val queryRefreshTokenPort = mockk<QueryRefreshTokenPort>()
    val queryAccountPort = mockk<QueryAccountPort>()
    val tokenGeneratePort = mockk<TokenGeneratePort>()
    val tokenParsePort = mockk<TokenParsePort>()
    val publisher = mockk<ApplicationEventPublisher>()
    val reissueTokenService = ReissueTokenService(queryRefreshTokenPort, queryAccountPort, tokenGeneratePort, tokenParsePort, publisher)

    Given("refreshToken이 주어질때") {
        val refreshToken = "Bear test refreshToken"
        val parsedRefreshToken = "test refreshToken"
        val refreshTokenDomain = AnyValueObjectGenerator.anyValueObject<RefreshToken>("refreshToken" to parsedRefreshToken)
        val account = AnyValueObjectGenerator.anyValueObject<Account>("accountIdx" to refreshTokenDomain.accountIdx)
        val tokenDto = AnyValueObjectGenerator.anyValueObject<TokenDto>("refreshToken" to parsedRefreshToken)
        val saveRefreshTokenEvent = SaveRefreshTokenEvent(parsedRefreshToken, refreshTokenDomain.accountIdx, 0)

        every { tokenParsePort.parseRefreshTokenToken(refreshToken) } returns parsedRefreshToken
        every { queryRefreshTokenPort.findByRefreshTokenOrNull(parsedRefreshToken) } returns refreshTokenDomain
        every { queryAccountPort.findByIdxOrNull(refreshTokenDomain.accountIdx) } returns account
        every { publisher.publishEvent(saveRefreshTokenEvent) } answers { nothing }
        every { tokenGeneratePort.generateToken(account.accountIdx, account.authority) } returns tokenDto

        When("토큰 재발급 요청을 하면") {
            val result = reissueTokenService.execute(refreshToken)

            Then("토큰이 재발급 되어야 한다.") {
                verify { tokenGeneratePort.generateToken(account.accountIdx, account.authority) }
            }

            Then("refreshToken save 이벤트가 발생 되어야 한다.") {
                verify { publisher.publishEvent(saveRefreshTokenEvent) }
            }

            Then("result와 tokenDto는 같아야 한다.") {
                result shouldBe tokenDto
            }
        }

        When("유효하지 않은 refreshToken으로 요청하면") {
            every { tokenParsePort.parseRefreshTokenToken(refreshToken) } returns null

            Then("InvalidTokenException이 터져야 한다.") {
                shouldThrow<InvalidTokenException> {
                    reissueTokenService.execute(refreshToken)
                }
            }
        }

        When("만료되서 삭제된 refreshToken으로 요청하면") {
            every { tokenParsePort.parseRefreshTokenToken(refreshToken) } returns parsedRefreshToken
            every { queryRefreshTokenPort.findByRefreshTokenOrNull(parsedRefreshToken) } returns null

            Then("ExpiredRefreshTokenException이 터져야 한다.") {
                shouldThrow<ExpiredRefreshTokenException> {
                    reissueTokenService.execute(refreshToken)
                }
            }
        }

        When("삭제된 계정으로 토큰 재발급을 요청하면") {
            every { tokenParsePort.parseRefreshTokenToken(refreshToken) } returns parsedRefreshToken
            every { queryRefreshTokenPort.findByRefreshTokenOrNull(parsedRefreshToken) } returns refreshTokenDomain
            every { queryAccountPort.findByIdxOrNull(refreshTokenDomain.accountIdx) } returns null

            Then("AccountNotFoundException이 터져야 한다.") {
                shouldThrow<AccountNotFoundException> {
                    reissueTokenService.execute(refreshToken)
                }
            }
        }
    }

})
