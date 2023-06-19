package com.project.indistraw.account.application.service

import com.project.indistraw.account.application.common.annotation.ServiceWithTransaction
import com.project.indistraw.account.application.common.util.AuthenticationValidator
import com.project.indistraw.account.application.exception.AccountNotFoundException
import com.project.indistraw.account.application.port.output.CommandAccountPort
import com.project.indistraw.account.application.port.output.QueryAccountPort
import com.project.indistraw.account.application.port.output.UpdatePhoneNumberUseCase
import com.project.indistraw.global.event.DeleteAuthenticationEvent
import org.springframework.context.ApplicationEventPublisher

@ServiceWithTransaction
class UpdatePhoneNumberService(
    private val commandAccountPort: CommandAccountPort,
    private val queryAccountPort: QueryAccountPort,
    private val authenticationValidator: AuthenticationValidator,
    private val publisher: ApplicationEventPublisher
): UpdatePhoneNumberUseCase {

    override fun execute(phoneNumber: String) {
        val account = queryAccountPort.findByPhoneNumberOrNull(phoneNumber)
            ?: throw AccountNotFoundException()

        val authentication = authenticationValidator.verifyAuthenticationByPhoneNumber(account.phoneNumber)
        publisher.publishEvent(DeleteAuthenticationEvent(authentication))

        commandAccountPort.saveAccount(account.copy(phoneNumber = phoneNumber))
    }

}