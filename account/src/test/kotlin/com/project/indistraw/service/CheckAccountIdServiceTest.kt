package com.project.indistraw.service

import com.project.indistraw.account.application.exception.DuplicatedAccountIdException
import com.project.indistraw.account.application.port.output.QueryAccountPort
import com.project.indistraw.account.application.service.CheckAccountIdService
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class CheckAccountIdServiceTest: BehaviorSpec({
    val queryAccountPort = mockk<QueryAccountPort>()
    val checkAccountIdService = CheckAccountIdService(queryAccountPort)

    Given("id가 주어질때") {
        val id = "test id"

        every { queryAccountPort.existsById(id) } returns false

        When("id 중복 검사 요청을 하면") {
            checkAccountIdService.execute(id)

            Then("id가 중복인지 판별이 되어야 한다.") {
                verify { queryAccountPort.existsById(id) }
            }
        }

        When("중복된 id로 요청을 하면") {
            every { queryAccountPort.existsById(id) } returns true

            Then("DuplicatedAccountIdException이 터져야 한다.") {
                shouldThrow<DuplicatedAccountIdException> {
                    checkAccountIdService.execute(id)
                }
            }
        }
    }
})