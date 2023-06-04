package com.project.indistraw.account.application.service

import com.project.indistraw.account.application.common.annotation.ServiceWithReadOnlyTransaction
import com.project.indistraw.account.application.exception.AccountNotFoundException
import com.project.indistraw.account.application.port.input.FindAccountIdUseCase
import com.project.indistraw.account.application.port.output.QueryAccountPort

@ServiceWithReadOnlyTransaction
class FindAccountIdService(
    private val queryAccountPort: QueryAccountPort
): FindAccountIdUseCase {

    override fun execute(phoneNumber: String): String {
        val account = queryAccountPort.findByPhoneNumberOrNull(phoneNumber)
            ?: throw AccountNotFoundException()
        return account.id
    }

}