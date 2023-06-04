package com.project.indistraw.service

import com.project.indistraw.account.application.exception.AccountNotFoundException
import com.project.indistraw.account.application.port.input.dto.UpdatePasswordDto
import com.project.indistraw.account.application.port.output.CommandAccountPort
import com.project.indistraw.account.application.port.output.PasswordEncodePort
import com.project.indistraw.account.application.port.output.QueryAccountPort
import com.project.indistraw.account.application.service.UpdatePasswordService
import com.project.indistraw.account.domain.Account
import com.project.indistraw.common.AnyValueObjectGenerator
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class UpdatePasswordServiceTest: BehaviorSpec({
    val commandAccountPort = mockk<CommandAccountPort>()
    val queryAccountPort = mockk<QueryAccountPort>()
    val passwordEncodePort = mockk<PasswordEncodePort>()
    val updatePasswordService = UpdatePasswordService(commandAccountPort, queryAccountPort, passwordEncodePort)

    Given("changePasswordDto가 주어질때") {
        val phoneNumber = "01012345678"
        val newPassword = "new test password"
        val encodedNewPassword = "new encoded test password"
        val updatePasswordDto = UpdatePasswordDto(phoneNumber, newPassword)
        val account = AnyValueObjectGenerator.anyValueObject<Account>("phoneNumber" to phoneNumber)

        every { queryAccountPort.findByPhoneNumberOrNull(updatePasswordDto.phoneNumber) } returns account
        every { passwordEncodePort.passwordEncode(updatePasswordDto.newPassword) } returns encodedNewPassword
        every { commandAccountPort.saveAccount(account.copy(encodedPassword = encodedNewPassword)) } returns account.accountIdx

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
    }
})
