package com.project.indistraw.service

import com.project.indistraw.account.application.exception.AuthenticationNotFoundException
import com.project.indistraw.account.application.port.input.dto.SignUpDto
import com.project.indistraw.account.application.port.output.CommandAccountPort
import com.project.indistraw.account.application.port.output.PasswordEncodePort
import com.project.indistraw.account.application.service.SignUpService
import com.project.indistraw.common.AnyValueObjectGenerator
import com.project.indistraw.account.application.port.output.QueryAccountPort
import com.project.indistraw.account.application.port.output.QueryAuthenticationPort
import com.project.indistraw.account.domain.Account
import com.project.indistraw.account.domain.Authentication
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.util.UUID

class SignUpServiceTest: BehaviorSpec({
    val commandAccountPort = mockk<CommandAccountPort>()
    val queryAccountPort = mockk<QueryAccountPort>()
    val queryAuthenticationPort = mockk<QueryAuthenticationPort>()
    val passwordEncodePort = mockk<PasswordEncodePort>()
    val signUpService = SignUpService(commandAccountPort, queryAccountPort, queryAuthenticationPort, passwordEncodePort)

    Given("signUpDto가 주어질때") {
        val signUpDto = AnyValueObjectGenerator.anyValueObject<SignUpDto>("id" to "mock id")
        val account = AnyValueObjectGenerator.anyValueObject<Account>("accountIdx" to UUID.randomUUID())
        val encodedPassword = "encoded password"
        val authentication = AnyValueObjectGenerator.anyValueObject<Authentication>("isVerified" to true)

        every { queryAuthenticationPort.findByPhoneNumberOrNull(signUpDto.phoneNumber) } returns authentication
        every { passwordEncodePort.passwordEncode(signUpDto.password) } returns encodedPassword
        every { queryAccountPort.existsById(signUpDto.id) } returns false
        every { commandAccountPort.saveAccount(any()) } returns account.accountIdx

        When("회원가입 요청을 하면") {
            signUpService.execute(signUpDto)

            Then("비밀번호가 인코딩 되어야 한다.") {
                verify { passwordEncodePort.passwordEncode(signUpDto.password) }
            }

            Then("계정이 생성이 되어야 한다.") {
                verify { commandAccountPort.saveAccount(any()) }
            }
        }

        When("인증되지 않은 사용자가 요청을 하면") {
            every { queryAuthenticationPort.findByPhoneNumberOrNull(signUpDto.phoneNumber) } returns null

            Then("AuthenticationNotFoundException이 터져야 한다.") {
                shouldThrow<AuthenticationNotFoundException> {
                    signUpService.execute(signUpDto)
                }
            }
        }
    }
})