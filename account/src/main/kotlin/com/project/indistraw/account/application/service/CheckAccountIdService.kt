package com.project.indistraw.account.application.service

import com.project.indistraw.account.application.common.annotation.ServiceWithReadOnlyTransaction
import com.project.indistraw.account.application.exception.DuplicatedAccountIdException
import com.project.indistraw.account.application.port.input.CheckAccountIdUseCase
import com.project.indistraw.account.application.port.output.QueryAccountPort

@ServiceWithReadOnlyTransaction
class CheckAccountIdService(
    private val queryAccountPort: QueryAccountPort
): CheckAccountIdUseCase {

    override fun execute(id: String) {
        if (queryAccountPort.existsById(id)) {
            throw DuplicatedAccountIdException()
        }
    }

}