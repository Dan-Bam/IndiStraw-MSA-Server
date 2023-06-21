package com.project.indistraw.service

import com.project.indistraw.account.application.exception.AccountNotFoundException
import com.project.indistraw.account.application.port.input.dto.AccountProfileDetailDto
import com.project.indistraw.account.application.port.output.AccountSecurityPort
import com.project.indistraw.account.application.port.output.QueryAccountPort
import com.project.indistraw.account.application.service.AccountProfileDetailService
import com.project.indistraw.account.domain.Account
import com.project.indistraw.common.AnyValueObjectGenerator
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import java.util.*

class AccountProfileDetailServiceTest: BehaviorSpec({
    val queryAccountPort = mockk<QueryAccountPort>()
    val accountSecurityPort = mockk<AccountSecurityPort>()
    val accountProfileDetailService = AccountProfileDetailService(queryAccountPort, accountSecurityPort)

    Given("계정이 주어질때") {
        val accountIdx = UUID.randomUUID()
        val account = AnyValueObjectGenerator.anyValueObject<Account>("accountIdx" to accountIdx)
        val accountProfileDetailDto = AccountProfileDetailDto(id = "", name = "", phoneNumber = "", address = null, profileUrl = "")

        every { accountSecurityPort.getCurrentAccountIdx() } returns accountIdx
        every { queryAccountPort.findByIdxOrNull(accountIdx) } returns account

        When("프로필 조회 요청을 하면") {
            val result = accountProfileDetailService.execute()

            Then("result와 accountProfileDetailDto는 같아야 한다.") {
                result shouldBe accountProfileDetailDto
            }
        }

        When("DB에 없는 accountIdx인 경우") {
            every { queryAccountPort.findByIdxOrNull(accountIdx) } returns null

            Then("AccountNotFoundException이 터져야한다.") {
                shouldThrow<AccountNotFoundException> {
                    accountProfileDetailService.execute()
                }
            }
        }
    }
})
