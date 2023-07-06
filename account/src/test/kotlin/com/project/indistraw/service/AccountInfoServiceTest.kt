package com.project.indistraw.service

import com.project.indistraw.account.application.exception.AccountNotFoundException
import com.project.indistraw.account.application.port.input.dto.AccountInfoDto
import com.project.indistraw.account.application.port.output.AccountSecurityPort
import com.project.indistraw.account.application.port.output.QueryAccountPort
import com.project.indistraw.account.application.service.FindAccountInfoService
import com.project.indistraw.account.domain.Account
import com.project.indistraw.common.AnyValueObjectGenerator
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import java.util.*

class AccountInfoServiceTest: BehaviorSpec({
    val queryAccountPort = mockk<QueryAccountPort>()
    val accountSecurityPort = mockk<AccountSecurityPort>()
    val findAccountInfoService = FindAccountInfoService(queryAccountPort, accountSecurityPort)

    Given("계정이 주어질때") {
        val accountIdx = UUID.randomUUID()
        val account = AnyValueObjectGenerator.anyValueObject<Account>("accountIdx" to accountIdx)
        val accountInfoDto = AccountInfoDto(accountIdx = accountIdx, id = "", name = "", phoneNumber = "", address = null, profileUrl = "")

        every { accountSecurityPort.getCurrentAccountIdx() } returns accountIdx
        every { queryAccountPort.findByIdxOrNull(accountIdx) } returns account

        When("프로필 조회 요청을 하면") {
            val result = findAccountInfoService.execute()

            Then("result와 accountInfoDto는 같아야 한다.") {
                result shouldBe accountInfoDto
            }
        }

        When("DB에 없는 accountIdx인 경우") {
            every { queryAccountPort.findByIdxOrNull(accountIdx) } returns null

            Then("AccountNotFoundException이 터져야한다.") {
                shouldThrow<AccountNotFoundException> {
                    findAccountInfoService.execute()
                }
            }
        }
    }
})
