package com.project.indistraw.account.application.service

import com.project.indistraw.account.application.common.annotation.ServiceWithTransaction
import com.project.indistraw.account.application.exception.AccountNotFoundException
import com.project.indistraw.account.application.port.input.AccountWithdrawUseCase
import com.project.indistraw.account.application.port.output.AccountSecurityPort
import com.project.indistraw.account.application.port.output.CommandAccountPort
import com.project.indistraw.account.application.port.output.QueryAccountPort
import com.project.indistraw.global.event.account.DeleteAccountPublishEvent
import org.springframework.context.ApplicationEventPublisher

@ServiceWithTransaction
class AccountWithdrawService(
    private val queryAccountPort: QueryAccountPort,
    private val commandAccountPort: CommandAccountPort,
    private val accountSecurityPort: AccountSecurityPort,
    private val publisher: ApplicationEventPublisher
): AccountWithdrawUseCase {

    override fun execute() {
        val accountIdx = accountSecurityPort.getCurrentAccountIdx()
        val account = queryAccountPort.findByIdxOrNull(accountIdx)
            ?: throw AccountNotFoundException()
        commandAccountPort.deleteAccount(account)

        // 계정을 논리 삭제 할때 각 서비스로 message를 publish 한다.
        val deleteAccountPublishEvent = DeleteAccountPublishEvent(account.accountIdx)
        publisher.publishEvent(deleteAccountPublishEvent)
    }

}