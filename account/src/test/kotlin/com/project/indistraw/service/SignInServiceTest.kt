package com.project.indistraw.service

import com.project.indistraw.account.application.exception.AccountNotFoundException
import com.project.indistraw.account.application.exception.PasswordNotMatchException
import com.project.indistraw.account.application.port.input.dto.SignInDto
import com.project.indistraw.account.application.port.output.JwtGeneratePort
import com.project.indistraw.account.application.port.output.PasswordEncodePort
import com.project.indistraw.account.application.port.output.QueryAccountPort
import com.project.indistraw.account.application.port.output.dto.TokenDto
import com.project.indistraw.account.application.service.SignInService
import com.project.indistraw.common.AnyValueObjectGenerator
import com.project.indistraw.account.domain.Account
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.util.*

class SignInServiceTest: BehaviorSpec({
    val queryAccountPort = mockk<QueryAccountPort>()
    val passwordEncodePort = mockk<PasswordEncodePort>()
    val jwtGeneratePort = mockk<JwtGeneratePort>()
    val signInService = SignInService(queryAccountPort, passwordEncodePort, jwtGeneratePort)

    Given("signInDto가 주어질때") {
        val signInDto = AnyValueObjectGenerator.anyValueObject<SignInDto>("id" to "mock id")
        val account = AnyValueObjectGenerator.anyValueObject<Account>("accountIdx" to UUID.randomUUID())
        val tokenDto = AnyValueObjectGenerator.anyValueObject<TokenDto>("accessToken" to "mock accessToken")

        every { queryAccountPort.findByIdOrNull(signInDto.id) } returns account
        every { passwordEncodePort.isPasswordMatch(signInDto.password, account.encodedPassword) } returns true
        every { jwtGeneratePort.generateToken(account.accountIdx, account.authority) } returns tokenDto

        When("로그인을 요청하면") {
            val result = signInService.execute(signInDto)

            Then("토큰이 생성 되어야 한다.") {
                verify { jwtGeneratePort.generateToken(account.accountIdx, account.authority) }
            }

            Then("result와 tokenDto는 같아야 한다.") {
                result shouldBe tokenDto
            }
        }

        When("없는 id로 요청하면") {
            every { queryAccountPort.findByIdOrNull(signInDto.id) } returns null

            Then("AccountNotFount 예외가 터져야 한다.") {
                shouldThrow<AccountNotFoundException> {
                    signInService.execute(signInDto)
                }
            }
        }

        When("틀린 비밀번호로 요청을 하면") {
            every { queryAccountPort.findByIdOrNull(signInDto.id) } returns account
            every { passwordEncodePort.isPasswordMatch(signInDto.password, account.encodedPassword) } returns false

            Then("PasswordNotMatchException 예외가 터져야 한다.") {
                shouldThrow<PasswordNotMatchException> {
                    signInService.execute(signInDto)
                }
            }
        }
    }
})