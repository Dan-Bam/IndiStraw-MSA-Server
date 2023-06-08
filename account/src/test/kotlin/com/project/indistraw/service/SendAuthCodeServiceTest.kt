package com.project.indistraw.service

import com.project.indistraw.account.application.port.output.CommandAuthCodePort
import com.project.indistraw.account.application.port.output.SendMessagePort
import com.project.indistraw.account.application.service.SendAuthCodeService
import com.project.indistraw.account.domain.Authentication
import com.project.indistraw.global.event.CreateAuthenticationEvent
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.context.ApplicationEventPublisher

class SendAuthCodeServiceTest: BehaviorSpec({
    val sendMessagePort = mockk<SendMessagePort>()
    val commandAuthCodePort = mockk<CommandAuthCodePort>()
    val publisher = mockk<ApplicationEventPublisher>()
    val sendAuthCodeService = SendAuthCodeService(sendMessagePort, commandAuthCodePort, publisher)

    Given("핸드폰 번호가 주어질때") {
        val phoneNumber = "01012345678"
        val code = 1234
        val authentication = Authentication(phoneNumber)

        every { sendMessagePort.sendMessage(any(), any()) } returns Unit
        every { publisher.publishEvent(CreateAuthenticationEvent(authentication)) } returns Unit
        every { commandAuthCodePort.saveAuthCode(any()) } returns code

        When("authCode 발송 요청을 하면") {
            sendAuthCodeService.execute(phoneNumber)

            Then("authCode가 발송이 되어야 한다.") {
                verify { sendMessagePort.sendMessage(any(), any()) }
            }
        }
    }
})
