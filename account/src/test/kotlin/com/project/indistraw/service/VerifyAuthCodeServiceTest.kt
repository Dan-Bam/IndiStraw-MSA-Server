package com.project.indistraw.service

import com.project.indistraw.account.application.exception.AuthCodeNotFoundException
import com.project.indistraw.account.application.exception.AuthCodeNotMatchException
import com.project.indistraw.account.application.exception.AuthenticationNotFoundException
import com.project.indistraw.account.application.port.output.QueryAuthCodePort
import com.project.indistraw.account.application.port.output.QueryAuthenticationPort
import com.project.indistraw.account.application.service.VerifyAuthCodeService
import com.project.indistraw.account.domain.AuthCode
import com.project.indistraw.account.domain.Authentication
import com.project.indistraw.common.AnyValueObjectGenerator
import com.project.indistraw.global.event.CreateAuthenticationEvent
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.context.ApplicationEventPublisher

class VerifyAuthCodeServiceTest: BehaviorSpec({
    val queryAuthCodePort = mockk<QueryAuthCodePort>()
    val queryAuthenticationPort = mockk<QueryAuthenticationPort>()
    val publisher = mockk<ApplicationEventPublisher>()
    val verifyAuthCodeService = VerifyAuthCodeService(queryAuthCodePort, queryAuthenticationPort, publisher)

    Given("authCode와 phoneNumber가 주어질때") {
        val authCode = 1234
        val phoneNumber = "01012345678"
        val authCodeDomain = AnyValueObjectGenerator.anyValueObject<AuthCode>("authCode" to authCode)
        val authentication = AnyValueObjectGenerator.anyValueObject<Authentication>("phoneNumber" to phoneNumber)
        val createAuthenticationEvent = CreateAuthenticationEvent(phoneNumber)

        every { queryAuthCodePort.findByPhoneNumberOrNull(phoneNumber) } returns authCodeDomain
        every { queryAuthenticationPort.findByPhoneNumberOrNull(phoneNumber) } returns authentication
        every { publisher.publishEvent(CreateAuthenticationEvent(phoneNumber, 1, false, 0)) } returns Unit
        every { publisher.publishEvent(createAuthenticationEvent.increaseCount()) } returns Unit
        every { publisher.publishEvent(createAuthenticationEvent.certified()) } returns Unit

        When("authCode 검증 요청을 하면") {
            verifyAuthCodeService.execute(authCode, phoneNumber)

            Then("authentication이 certified 되어야 한다.") {
                verify { publisher.publishEvent(createAuthenticationEvent.certified()) }
            }
        }

        When("틀린 authCode로 검증 요청을 하면") {
            val wrongAuthCode = 4321

            Then("AuthCodeNotMatchException이 터져야 한다.") {
                shouldThrow<AuthCodeNotMatchException> {
                    verifyAuthCodeService.execute(wrongAuthCode, phoneNumber)
                }
            }
        }

        When("찾을 수 없는 authCode로 요청을 하면") {
            every { queryAuthCodePort.findByPhoneNumberOrNull(phoneNumber) } returns null

            Then("AuthCodeNotFoundException이 터져야 한다.") {
                shouldThrow<AuthCodeNotFoundException> {
                    verifyAuthCodeService.execute(authCode, phoneNumber)
                }
            }
        }

        When("인증 되지 않은 phoneNumber로 요청을 하면") {
            every { queryAuthCodePort.findByPhoneNumberOrNull(phoneNumber) } returns authCodeDomain
            every { queryAuthenticationPort.findByPhoneNumberOrNull(phoneNumber) } returns null

            Then("AuthenticationNotFoundException이 터져야 한다.") {
                shouldThrow<AuthenticationNotFoundException> {
                    verifyAuthCodeService.execute(authCode, phoneNumber)
                }
            }
        }
    }

})
