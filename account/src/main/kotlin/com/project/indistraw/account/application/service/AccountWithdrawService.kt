package com.project.indistraw.account.application.service

import com.project.indistraw.account.application.common.annotation.ServiceWithTransaction
import com.project.indistraw.account.application.exception.AccountNotFoundException
import com.project.indistraw.account.application.port.input.AccountWithdrawUseCase
import com.project.indistraw.account.application.port.output.AccountSecurityPort
import com.project.indistraw.account.application.port.output.CommandAccountPort
import com.project.indistraw.account.application.port.output.QueryAccountPort

@ServiceWithTransaction
class AccountWithdrawService(
    private val queryAccountPort: QueryAccountPort,
    private val commandAccountPort: CommandAccountPort,
    private val accountSecurityPort: AccountSecurityPort
): AccountWithdrawUseCase {

    override fun execute() {
        val accountIdx = accountSecurityPort.getCurrentAccountIdx()
        val account = queryAccountPort.findByIdxOrNull(accountIdx)
            ?: throw AccountNotFoundException()
        commandAccountPort.deleteAccount(account)
    }

}