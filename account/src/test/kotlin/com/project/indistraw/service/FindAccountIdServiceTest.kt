package com.project.indistraw.service

import com.project.indistraw.account.application.common.util.AuthenticationValidator
import com.project.indistraw.account.application.exception.AccountNotFoundException
import com.project.indistraw.account.application.port.output.QueryAccountPort
import com.project.indistraw.account.application.service.FindAccountIdService
import com.project.indistraw.account.domain.Account
import com.project.indistraw.account.domain.Authentication
import com.project.indistraw.common.AnyValueObjectGenerator
import com.project.indistraw.global.event.DeleteAuthenticationEvent
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.springframework.context.ApplicationEventPublisher

class FindAccountIdServiceTest: BehaviorSpec({
    val queryAccountPort = mockk<QueryAccountPort>()
    val authenticationValidator = mockk<AuthenticationValidator>()
    val publisher = mockk<ApplicationEventPublisher>()
    val findAccountIdService = FindAccountIdService(queryAccountPort, authenticationValidator, publisher)

    Given("phoneNumber가 주어질떄") {
        val phoneNumber = "01012345678"
        val id = "test id"
        val account = AnyValueObjectGenerator.anyValueObject<Account>("id" to id)
        val authentication = AnyValueObjectGenerator.anyValueObject<Authentication>("phoneNumber" to phoneNumber)

        every { queryAccountPort.findByPhoneNumberOrNull(phoneNumber) } returns account
        every { authenticationValidator.verifyAuthenticationByPhoneNumber(phoneNumber) } returns authentication
        every { publisher.publishEvent(DeleteAuthenticationEvent(authentication)) } returns Unit

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
