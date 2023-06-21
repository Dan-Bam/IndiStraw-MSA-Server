package com.project.indistraw.service

import com.project.indistraw.account.application.common.util.AuthenticationValidator
import com.project.indistraw.account.application.exception.AccountNotFoundException
import com.project.indistraw.account.application.port.output.AccountSecurityPort
import com.project.indistraw.account.application.port.output.CommandAccountPort
import com.project.indistraw.account.application.port.output.QueryAccountPort
import com.project.indistraw.account.application.service.UpdatePhoneNumberService
import com.project.indistraw.account.domain.Account
import com.project.indistraw.account.domain.Authentication
import com.project.indistraw.common.AnyValueObjectGenerator
import com.project.indistraw.global.event.DeleteAuthenticationEvent
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.context.ApplicationEventPublisher
import java.util.UUID

class UpdatePhoneNumberServiceTest: BehaviorSpec({
    val commandAccountPort = mockk<CommandAccountPort>()
    val queryAccountPort = mockk<QueryAccountPort>()
    val authenticationValidator = mockk<AuthenticationValidator>()
    val publisher = mockk<ApplicationEventPublisher>()
    val accountSecurityPort = mockk<AccountSecurityPort>()
    val updatePhoneNumberService = UpdatePhoneNumberService(commandAccountPort, queryAccountPort, authenticationValidator, accountSecurityPort, publisher)

    Given("phoneNumber가 주어질때") {
        val phoneNumber = "01012345678"
        val accountIdx = UUID.randomUUID()
        val account = AnyValueObjectGenerator.anyValueObject<Account>("phoneNumber" to phoneNumber)
        val authentication = AnyValueObjectGenerator.anyValueObject<Authentication>("phoneNumber" to phoneNumber)
        val deleteAuthenticationEvent = DeleteAuthenticationEvent(authentication)

        every { queryAccountPort.findByIdxOrNull(accountIdx) } returns account
        every { authenticationValidator.verifyAuthenticationByPhoneNumber(account.phoneNumber) } returns authentication
        every { publisher.publishEvent(deleteAuthenticationEvent) } returns Unit
        every { commandAccountPort.saveAccount(account.copy(phoneNumber = phoneNumber)) } returns account.accountIdx
        every { accountSecurityPort.getCurrentAccountIdx() } returns accountIdx

        When("전화번호 수정 요청을 하면") {
            updatePhoneNumberService.execute(phoneNumber)

            Then("전화번호가 변경이 되어야 한다.") {
                verify { commandAccountPort.saveAccount(account.copy(phoneNumber = phoneNumber)) }
            }
        }

        When("없는 accountIdx 요청하면") {
            every { queryAccountPort.findByIdxOrNull(accountIdx) } returns null

            Then("AccountNotFound가 터져야 한다.") {
                shouldThrow<AccountNotFoundException> {
                    updatePhoneNumberService.execute(phoneNumber)
                }
            }
        }
    }

})