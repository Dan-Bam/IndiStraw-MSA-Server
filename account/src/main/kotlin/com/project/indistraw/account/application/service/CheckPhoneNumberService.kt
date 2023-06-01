package com.project.indistraw.account.application.service

import com.project.indistraw.account.application.common.annotation.ServiceWithReadOnlyTransaction
import com.project.indistraw.account.application.exception.DuplicatedPhoneNumberException
import com.project.indistraw.account.application.port.input.CheckPhoneNumberUseCase
import com.project.indistraw.account.application.port.output.QueryAccountPort

@ServiceWithReadOnlyTransaction
class CheckPhoneNumberService(
    private val queryAccountPort: QueryAccountPort
): CheckPhoneNumberUseCase {

    override fun execute(phoneNumber: String) {
        if (queryAccountPort.existsByPhoneNumber(phoneNumber)) {
            throw DuplicatedPhoneNumberException()
        }
    }


}