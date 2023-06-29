package com.project.indistraw.service

import com.project.indistraw.account.application.exception.AccountNotFoundException
import com.project.indistraw.account.application.port.output.AccountSecurityPort
import com.project.indistraw.account.application.port.output.CommandAccountPort
import com.project.indistraw.account.application.port.output.QueryAccountPort
import com.project.indistraw.account.application.service.AccountWithdrawService
import com.project.indistraw.account.domain.Account
import com.project.indistraw.common.AnyValueObjectGenerator
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.context.ApplicationEventPublisher
import java.util.*

class AccountWithdrawServiceTest : BehaviorSpec({
    val queryAccountPort = mockk<QueryAccountPort>()
    val commandAccountPort = mockk<CommandAccountPort>()
    val accountSecurityPort = mockk<AccountSecurityPort>()
    val publisher = mockk<ApplicationEventPublisher>()
    val accountWithdrawService = AccountWithdrawService(queryAccountPort, commandAccountPort, accountSecurityPort, publisher)

    Given("계정이 주어질때") {
        val accountIdx = UUID.randomUUID()
        val account = AnyValueObjectGenerator.anyValueObject<Account>()

        every { accountSecurityPort.getCurrentAccountIdx() } returns accountIdx
        every { queryAccountPort.findByIdxOrNull(accountIdx) } returns account
        every { commandAccountPort.deleteAccount(account) } returns Unit

        When("계정 삭제 요청을 하면") {
            accountWithdrawService.execute()

            Then("계정이 삭제가 되어야 한다.") {
                verify { commandAccountPort.deleteAccount(account) }
            }
        }

        When("DB에 없는 accountIdx인 경우") {
            every { queryAccountPort.findByIdxOrNull(accountIdx) } returns null

            Then("AccountNotFoundException이 터져야한다.") {
                shouldThrow<AccountNotFoundException> {
                    accountWithdrawService.execute()
                }
            }
        }
    }
})
