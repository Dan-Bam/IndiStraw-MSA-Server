package com.project.indistraw.account.application.service

import com.project.indistraw.account.application.common.annotation.ServiceWithTransaction
import com.project.indistraw.account.application.common.util.AuthenticationValidator
import com.project.indistraw.account.application.exception.DuplicatedAccountIdException
import com.project.indistraw.account.application.port.input.SignUpUseCase
import com.project.indistraw.account.application.port.input.dto.SignUpDto
import com.project.indistraw.account.application.port.output.CommandAccountPort
import com.project.indistraw.account.application.port.output.PasswordEncodePort
import com.project.indistraw.account.application.port.output.QueryAccountPort
import com.project.indistraw.account.domain.Account
import com.project.indistraw.account.domain.Authority
import com.project.indistraw.global.event.DeleteAuthenticationEvent
import org.springframework.context.ApplicationEventPublisher
import java.util.*

@ServiceWithTransaction
class SignUpService(
    private val commandAccountPort: CommandAccountPort,
    private val queryAccountPort: QueryAccountPort,
    private val passwordEncodePort: PasswordEncodePort,
    private val authenticationValidator: AuthenticationValidator,
    private val publisher: ApplicationEventPublisher
): SignUpUseCase {

    override fun execute(dto: SignUpDto): UUID {
        if (queryAccountPort.existsById(dto.id)) throw DuplicatedAccountIdException()

        // 인증된 사용자라면 확인 후, authentication 삭제 이벤트를 발행합니다.
        val authentication = authenticationValidator.verifyAuthenticationByPhoneNumber(dto.phoneNumber)
        val deleteAuthenticationEvent = DeleteAuthenticationEvent(authentication)
        publisher.publishEvent(deleteAuthenticationEvent)

        val account = dto.let {
            Account(
                accountIdx = UUID.randomUUID(),
                id = it.id,
                encodedPassword = passwordEncodePort.passwordEncode(dto.password),
                name = it.name,
                phoneNumber = it.phoneNumber,
                address = null,
                profileUrl = it.profileUrl,
                authority = Authority.ROLE_ACCOUNT
            )
        }

        return commandAccountPort.saveAccount(account)
    }

}