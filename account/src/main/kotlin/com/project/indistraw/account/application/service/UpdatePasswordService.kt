package com.project.indistraw.account.application.service

import com.project.indistraw.account.application.common.annotation.ServiceWithTransaction
import com.project.indistraw.account.application.exception.AccountNotFoundException
import com.project.indistraw.account.application.port.input.UpdatePasswordUseCase
import com.project.indistraw.account.application.port.input.dto.UpdatePasswordDto
import com.project.indistraw.account.application.port.output.CommandAccountPort
import com.project.indistraw.account.application.port.output.PasswordEncodePort
import com.project.indistraw.account.application.port.output.QueryAccountPort

@ServiceWithTransaction
class UpdatePasswordService(
    private val commandAccountPort: CommandAccountPort,
    private val queryAccountPort: QueryAccountPort,
    private val passwordEncodePort: PasswordEncodePort
): UpdatePasswordUseCase {

    override fun execute(dto: UpdatePasswordDto) {
        val account = queryAccountPort.findByPhoneNumberOrNull(dto.phoneNumber)
            ?: throw AccountNotFoundException()
        val encodedNewPassword = passwordEncodePort.passwordEncode(dto.newPassword)
        commandAccountPort.saveAccount(account.copy(encodedPassword = encodedNewPassword))
    }

}