package com.project.indistraw.service

import com.project.indistraw.account.application.exception.AccountNotFoundException
import com.project.indistraw.account.application.port.output.QueryAccountPort
import com.project.indistraw.account.application.service.FindAccountIdService
import com.project.indistraw.account.domain.Account
import com.project.indistraw.common.AnyValueObjectGenerator
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class FindAccountIdServiceTest : BehaviorSpec({
    val queryAccountPort = mockk<QueryAccountPort>()
    val findAccountIdService = FindAccountIdService(queryAccountPort)

    Given("phoneNumber가 주어질떄") {
        val phoneNumber = "01012345678"
        val id = "test id"
        val account = AnyValueObjectGenerator.anyValueObject<Account>("id" to id)

        every { queryAccountPort.findByPhoneNumberOrNull(phoneNumber) } returns account

        When("id 찾기 요청을 하면") {
            val result = findAccountIdService.execute(phoneNumber)

            Then("result와 id는 같아야 한다.") {
                result shouldBe account.id
            }
        }

        When("DB에 없는 phoneNumber로 요청한 경우") {
            every { queryAccountPort.findByPhoneNumberOrNull(phoneNumber) } returns null

            Then("AccountNotFoundException이 터져야한다.") {
                shouldThrow<AccountNotFoundException> {
                    findAccountIdService.execute(phoneNumber)
                }
            }
        }
    }
})
