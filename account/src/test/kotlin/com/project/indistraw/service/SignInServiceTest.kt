package com.project.indistraw.service

import com.project.indistraw.account.application.exception.AccountNotFoundException
import com.project.indistraw.account.application.exception.PasswordNotMatchException
import com.project.indistraw.account.application.port.input.dto.SignInDto
import com.project.indistraw.account.application.port.output.TokenGeneratePort
import com.project.indistraw.account.application.port.output.PasswordEncodePort
import com.project.indistraw.account.application.port.output.QueryAccountPort
import com.project.indistraw.account.application.port.output.dto.TokenDto
import com.project.indistraw.account.application.service.SignInService
import com.project.indistraw.common.AnyValueObjectGenerator
import com.project.indistraw.account.domain.Account
import com.project.indistraw.global.event.SaveRefreshTokenEvent
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.context.ApplicationEventPublisher
import java.util.*

class SignInServiceTest: BehaviorSpec({
    val queryAccountPort = mockk<QueryAccountPort>()
    val passwordEncodePort = mockk<PasswordEncodePort>()
    val tokenGeneratePort = mockk<TokenGeneratePort>()
    val publisher = mockk<ApplicationEventPublisher>(relaxed = true)
    val signInService = SignInService(queryAccountPort, passwordEncodePort, tokenGeneratePort, publisher)

    Given("signInDto가 주어질때") {
        val signInDto = AnyValueObjectGenerator.anyValueObject<SignInDto>("id" to "mock id")
        val account = AnyValueObjectGenerator.anyValueObject<Account>("accountIdx" to UUID.randomUUID())
        val refreshToken = "test refreshToken"
        val tokenDto = AnyValueObjectGenerator.anyValueObject<TokenDto>("refreshToken" to refreshToken)
        val saveRefreshTokenEvent = SaveRefreshTokenEvent(refreshToken, account.accountIdx, 0)

        every { queryAccountPort.findByIdOrNull(signInDto.id) } returns account
        every { passwordEncodePort.isPasswordMatch(signInDto.password, account.encodedPassword) } returns true
        every { publisher.publishEvent(saveRefreshTokenEvent) } returns  Unit
        every { tokenGeneratePort.generateToken(account.accountIdx, account.authority) } returns tokenDto

        When("로그인을 요청하면") {
            val result = signInService.execute(signInDto)

            Then("토큰이 생성 되어야 한다.") {
                verify { tokenGeneratePort.generateToken(account.accountIdx, account.authority) }
            }

            Then("refreshToken save 이벤트가 발생해야한다.") {
                verify { publisher.publishEvent(saveRefreshTokenEvent) }
            }

            Then("result와 tokenDto는 같아야 한다.") {
                result shouldBe tokenDto
            }
        }

        When("없는 id로 요청하면") {
            every { queryAccountPort.findByIdOrNull(signInDto.id) } returns null

            Then("AccountNotFound가 터져야 한다.") {
                shouldThrow<AccountNotFoundException> {
                    signInService.execute(signInDto)
                }
            }
        }

        When("틀린 비밀번호로 요청을 하면") {
            every { queryAccountPort.findByIdOrNull(signInDto.id) } returns account
            every { passwordEncodePort.isPasswordMatch(signInDto.password, account.encodedPassword) } returns false

            Then("PasswordNotMatchException이 터져야 한다.") {
                shouldThrow<PasswordNotMatchException> {
                    signInService.execute(signInDto)
                }
            }
        }
    }
})