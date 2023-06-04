package com.project.indistraw.account.application.service

import com.project.indistraw.account.application.common.annotation.ServiceWithTransaction
import com.project.indistraw.account.application.port.input.SendAuthCodeUseCase
import com.project.indistraw.account.application.port.output.CommandAuthCodePort
import com.project.indistraw.account.application.port.output.SendMessagePort
import com.project.indistraw.account.domain.AuthCode
import com.project.indistraw.global.event.CreateAuthenticationEvent
import org.springframework.context.ApplicationEventPublisher
import java.util.*

@ServiceWithTransaction
class SendAuthCodeService(
    private val sendMessagePort: SendMessagePort,
    private val commandAuthCodePort: CommandAuthCodePort,
    private val publisher: ApplicationEventPublisher
): SendAuthCodeUseCase {

    override fun execute(phoneNumber: String) {
        val code = createCode()
        sendMessagePort.sendMessage(phoneNumber, code)
        val authCode = AuthCode(
            phoneNumber = phoneNumber,
            authCode = code,
            expiredAt = 300
        )
        commandAuthCodePort.saveAuthCode(authCode)
        publisher.publishEvent(CreateAuthenticationEvent(phoneNumber))
    }

    private fun createCode() = Random().nextInt(8888) + 1111

}