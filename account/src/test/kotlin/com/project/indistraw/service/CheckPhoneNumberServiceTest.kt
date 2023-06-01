package com.project.indistraw.service

import com.project.indistraw.account.application.exception.DuplicatedPhoneNumberException
import com.project.indistraw.account.application.port.output.QueryAccountPort
import com.project.indistraw.account.application.service.CheckPhoneNumberService
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk

class CheckPhoneNumberServiceTest: BehaviorSpec({
    val queryAccountPort = mockk<QueryAccountPort>()
    val checkPhoneNumberService = CheckPhoneNumberService(queryAccountPort)

    Given("전화번호가 주어질때") {
        val phoneNumber = "01012345678"

        every { queryAccountPort.existsByPhoneNumber(phoneNumber) } returns false

        When("전화번호 중복 검사 요청을 하면") {
            checkPhoneNumberService.execute(phoneNumber)

            Then("전화번호가 중복인지 판별이 되어야 한다.") {

            }
        }

        When("중복된 전화번호로 요청을 하면") {
            every { queryAccountPort.existsByPhoneNumber(phoneNumber) } returns true

            Then("DuplicatePhoneNumberException이 터져야 한다.") {
                shouldThrow<DuplicatedPhoneNumberException> {
                    checkPhoneNumberService.execute(phoneNumber)
                }
            }
        }
    }
})