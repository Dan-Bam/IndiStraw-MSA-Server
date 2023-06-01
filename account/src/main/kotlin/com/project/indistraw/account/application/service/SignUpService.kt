package com.project.indistraw.account.application.service

import com.project.indistraw.account.application.common.annotation.ServiceWithTransaction
import com.project.indistraw.account.application.exception.AccountNotFoundException
import com.project.indistraw.account.application.port.input.SignUpUseCase
import com.project.indistraw.account.application.port.input.dto.SignUpDto
import com.project.indistraw.account.application.port.output.CommandAccountPort
import com.project.indistraw.account.application.port.output.PasswordEncodePort
import com.project.indistraw.account.application.port.output.QueryAccountPort
import com.project.indistraw.account.domain.Account
import com.project.indistraw.account.domain.Authority
import java.util.*

@ServiceWithTransaction
class SignUpService(
    private val commandAccountPort: CommandAccountPort,
    private val queryAccountPort: QueryAccountPort,
    private val passwordEncodePort: PasswordEncodePort
): SignUpUseCase {

    override fun execute(dto: SignUpDto): UUID {
        if (queryAccountPort.existsById(dto.id)) {
            throw AccountNotFoundException()
        }

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