package com.project.indistraw.service

import com.project.indistraw.account.application.exception.AccountNotFoundException
import com.project.indistraw.account.application.port.input.dto.UpdateAccountProfileDto
import com.project.indistraw.account.application.port.output.AccountSecurityPort
import com.project.indistraw.account.application.port.output.CommandAccountPort
import com.project.indistraw.account.application.port.output.QueryAccountPort
import com.project.indistraw.account.application.service.UpdateAccountProfileService
import com.project.indistraw.account.domain.Account
import com.project.indistraw.common.AnyValueObjectGenerator
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.util.*

class UpdateAccountProfileServiceTest: BehaviorSpec({
    val queryAccountPort = mockk<QueryAccountPort>()
    val commandAccountPort = mockk<CommandAccountPort>()
    val accountSecurityPort = mockk<AccountSecurityPort>()
    val updateAccountProfileService = UpdateAccountProfileService(queryAccountPort, commandAccountPort, accountSecurityPort)

    Given("updateAccountProfileDto이 주어질때") {
        val phoneNumber = "01012345678"
        val updateAccountProfileDto = AnyValueObjectGenerator.anyValueObject<UpdateAccountProfileDto>("phoneNumber" to phoneNumber)
        val accountIdx = UUID.randomUUID()
        val account = AnyValueObjectGenerator.anyValueObject<Account>("accountIdx" to accountIdx)

        every { accountSecurityPort.getCurrentAccountIdx() } returns accountIdx
        every { queryAccountPort.findByIdxOrNull(accountIdx) } returns account
        every { commandAccountPort.saveAccount(any()) } returns account.accountIdx

        When("프로필 수정 요청을 하면") {
            updateAccountProfileService.execute(updateAccountProfileDto)

            Then("프로필 수정이 되어야 한다.") {
                verify { commandAccountPort.saveAccount(any()) }
            }
        }

        When("없는 accountIdx로 요청하면") {
            every { queryAccountPort.findByIdxOrNull(accountIdx) } returns null

            Then("AccountNotFound가 터져야 한다.") {
                shouldThrow<AccountNotFoundException> {
                    updateAccountProfileService.execute(updateAccountProfileDto)
                }
            }
        }
    }

})
