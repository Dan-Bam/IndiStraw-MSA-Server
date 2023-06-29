package com.project.indistraw.service

import com.project.indistraw.account.application.common.util.AuthenticationValidator
import com.project.indistraw.account.application.exception.DuplicatedAccountIdException
import com.project.indistraw.account.application.port.input.dto.SignUpDto
import com.project.indistraw.account.application.port.output.CommandAccountPort
import com.project.indistraw.account.application.port.output.PasswordEncodePort
import com.project.indistraw.account.application.port.output.QueryAccountPort
import com.project.indistraw.account.application.service.SignUpService
import com.project.indistraw.account.domain.Account
import com.project.indistraw.account.domain.Authentication
import com.project.indistraw.common.AnyValueObjectGenerator
import com.project.indistraw.global.event.authentication.DeleteAuthenticationEvent
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.context.ApplicationEventPublisher
import java.util.*

class SignUpServiceTest: BehaviorSpec({
    val commandAccountPort = mockk<CommandAccountPort>()
    val queryAccountPort = mockk<QueryAccountPort>()
    val passwordEncodePort = mockk<PasswordEncodePort>()
    val authenticationValidator = mockk<AuthenticationValidator>()
    val publisher = mockk<ApplicationEventPublisher>()
    val signUpService = SignUpService(commandAccountPort, queryAccountPort, passwordEncodePort, authenticationValidator, publisher)

    Given("signUpDto가 주어질때") {
        val signUpDto = AnyValueObjectGenerator.anyValueObject<SignUpDto>("id" to "mock id")
        val account = AnyValueObjectGenerator.anyValueObject<Account>("accountIdx" to UUID.randomUUID())
        val encodedPassword = "encoded password"
        val authentication = AnyValueObjectGenerator.anyValueObject<Authentication>("isVerified" to false)
        val deleteAuthenticationEvent = DeleteAuthenticationEvent(authentication)

        every { passwordEncodePort.passwordEncode(signUpDto.password) } returns encodedPassword
        every { queryAccountPort.existsById(signUpDto.id) } returns false
        every { commandAccountPort.saveAccount(any()) } returns account.accountIdx
        every { authenticationValidator.verifyAuthenticationByPhoneNumber(signUpDto.phoneNumber) } returns authentication
        every { publisher.publishEvent(deleteAuthenticationEvent) } returns Unit

        When("회원가입 요청을 하면") {
            signUpService.execute(signUpDto)

            Then("비밀번호가 인코딩 되어야 한다.") {
                verify { passwordEncodePort.passwordEncode(signUpDto.password) }
            }

            Then("계정이 생성이 되어야 한다.") {
                verify { commandAccountPort.saveAccount(any()) }
            }
        }

        When("이미 DB에 있는 id로 요청을 하면") {
            every { queryAccountPort.existsById(signUpDto.id) } returns true

            Then("DuplicatedAccountIdException이 터져야 한다.") {
                shouldThrow<DuplicatedAccountIdException> {
                    signUpService.execute(signUpDto)
                }
            }
        }
    }
})