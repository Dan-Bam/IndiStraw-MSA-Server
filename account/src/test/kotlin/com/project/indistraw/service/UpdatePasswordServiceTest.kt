package com.project.indistraw.service

import com.project.indistraw.account.application.common.util.AuthenticationValidator
import com.project.indistraw.account.application.exception.AccountNotFoundException
import com.project.indistraw.account.application.exception.DuplicatedNewPasswordException
import com.project.indistraw.account.application.port.input.dto.UpdatePasswordDto
import com.project.indistraw.account.application.port.output.CommandAccountPort
import com.project.indistraw.account.application.port.output.PasswordEncodePort
import com.project.indistraw.account.application.port.output.QueryAccountPort
import com.project.indistraw.account.application.service.UpdatePasswordService
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

class UpdatePasswordServiceTest: BehaviorSpec({
    val commandAccountPort = mockk<CommandAccountPort>()
    val queryAccountPort = mockk<QueryAccountPort>()
    val passwordEncodePort = mockk<PasswordEncodePort>()
    val authenticationValidator = mockk<AuthenticationValidator>()
    val publisher = mockk<ApplicationEventPublisher>()
    val updatePasswordService = UpdatePasswordService(commandAccountPort, queryAccountPort, passwordEncodePort, authenticationValidator, publisher)

    Given("changePasswordDto가 주어질때") {
        val phoneNumber = "01012345678"
        val newPassword = "new test password"
        val encodedNewPassword = "new encoded test password"
        val updatePasswordDto = UpdatePasswordDto(phoneNumber, newPassword)
        val account = AnyValueObjectGenerator.anyValueObject<Account>("phoneNumber" to phoneNumber)
        val authentication = AnyValueObjectGenerator.anyValueObject<Authentication>("phoneNumber" to phoneNumber)

        every { queryAccountPort.findByPhoneNumberOrNull(updatePasswordDto.phoneNumber) } returns account
        every { passwordEncodePort.isPasswordMatch(newPassword, account.encodedPassword) } returns false
        every { passwordEncodePort.passwordEncode(updatePasswordDto.newPassword) } returns encodedNewPassword
        every { commandAccountPort.saveAccount(account.copy(encodedPassword = encodedNewPassword)) } returns account.accountIdx
        every { authenticationValidator.verifyAuthenticationByPhoneNumber(phoneNumber) } returns authentication
        every { publisher.publishEvent(DeleteAuthenticationEvent(authentication)) } returns Unit

        When("비밀번호 변경 요청을 하면") {
            updatePasswordService.execute(updatePasswordDto)

            Then("비밀번호 변경이 되어야 한다.") {
                verify { commandAccountPort.saveAccount(account.copy(encodedPassword = encodedNewPassword)) }
            }
        }

        When("없는 phoneNumber로 요청하면") {
            every { queryAccountPort.findByPhoneNumberOrNull(updatePasswordDto.phoneNumber) } returns null

            Then("AccountNotFound가 터져야 한다.") {
                shouldThrow<AccountNotFoundException> {
                    updatePasswordService.execute(updatePasswordDto)
                }
            }
        }

        When("기존 비밀번호와 같게 새 비밀번호를 요청하면") {
            every { queryAccountPort.findByPhoneNumberOrNull(updatePasswordDto.phoneNumber) } returns account
            every { passwordEncodePort.isPasswordMatch(newPassword, account.encodedPassword) } returns true

            Then("DuplicatedNewPasswordException이 터져야 한다.") {
                shouldThrow<DuplicatedNewPasswordException> {
                    updatePasswordService.execute(updatePasswordDto)
                }
            }
        }
    }
})
