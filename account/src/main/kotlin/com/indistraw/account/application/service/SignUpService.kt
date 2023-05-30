package com.indistraw.account.application.service

import com.indistraw.account.application.common.annotation.ServiceWithTransaction
import com.indistraw.account.application.port.input.SignUpUseCase
import com.indistraw.account.application.port.input.dto.SignUpDto
import com.indistraw.account.application.port.output.CommandAccountPort
import com.indistraw.account.application.port.output.PasswordEncodePort
import com.indistraw.account.domain.Account
import com.indistraw.account.domain.Authority
import java.util.*

@ServiceWithTransaction
class SignUpService(
    private val commandAccountPort: CommandAccountPort,
    private val passwordEncodePort: PasswordEncodePort
): SignUpUseCase {

    override fun execute(dto: SignUpDto): UUID {
        val encodedPassword = passwordEncodePort.passwordEncode(dto.password)
        val account = dto.let {
            Account(
                accountIdx = UUID.randomUUID(),
                id = it.id,
                encodedPassword = encodedPassword,
                name = it.name,
                phoneNumber = it.phoneNumber,
                profileUrl = it.profileUrl,
                authority = Authority.ROLE_ACCOUNT
            )
        }

        return commandAccountPort.saveAccount(account)
    }

}