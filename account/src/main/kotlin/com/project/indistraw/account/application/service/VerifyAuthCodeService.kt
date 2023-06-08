package com.project.indistraw.account.application.service

import com.project.indistraw.account.application.common.annotation.ServiceWithTransaction
import com.project.indistraw.account.application.exception.AuthCodeNotFoundException
import com.project.indistraw.account.application.exception.AuthCodeNotMatchException
import com.project.indistraw.account.application.exception.AuthenticationNotFoundException
import com.project.indistraw.account.application.exception.TooManyAuthenticationRequestException
import com.project.indistraw.account.application.port.input.VerifyAuthCodeUseCase
import com.project.indistraw.account.application.port.output.QueryAuthCodePort
import com.project.indistraw.account.application.port.output.QueryAuthenticationPort
import com.project.indistraw.global.event.CreateAuthenticationEvent
import org.springframework.context.ApplicationEventPublisher

@ServiceWithTransaction
class VerifyAuthCodeService(
    private val queryAuthCodePort: QueryAuthCodePort,
    private val queryAuthenticationPort: QueryAuthenticationPort,
    private val publisher: ApplicationEventPublisher,
): VerifyAuthCodeUseCase {

    override fun execute(authCode: Int, phoneNumber: String) {
        val authCodeDomain = queryAuthCodePort.findByPhoneNumberOrNull(phoneNumber)
            ?: throw AuthCodeNotFoundException()
        val authentication = queryAuthenticationPort.findByPhoneNumberOrNull(phoneNumber)
            ?: throw AuthenticationNotFoundException()

        if (authentication.attemptCount > 5) throw TooManyAuthenticationRequestException()

        if (authCodeDomain.authCode != authCode) {
            publisher.publishEvent(CreateAuthenticationEvent(authentication.increaseCount()))
            throw AuthCodeNotMatchException()
        }

        publisher.publishEvent(CreateAuthenticationEvent(authentication.certified()))
    }

}