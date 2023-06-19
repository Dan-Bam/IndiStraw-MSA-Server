package com.project.indistraw.account.application.service

import com.project.indistraw.account.application.common.annotation.ServiceWithReadOnlyTransaction
import com.project.indistraw.account.application.common.enums.CheckPhoneNumberType
import com.project.indistraw.account.application.exception.AccountNotFoundException
import com.project.indistraw.account.application.exception.DuplicatedPhoneNumberException
import com.project.indistraw.account.application.port.input.CheckPhoneNumberUseCase
import com.project.indistraw.account.application.port.output.QueryAccountPort

@ServiceWithReadOnlyTransaction
class CheckPhoneNumberService(
    private val queryAccountPort: QueryAccountPort
): CheckPhoneNumberUseCase {

    override fun execute(phoneNumber: String, type: CheckPhoneNumberType) {
        when(type) {
            CheckPhoneNumberType.SIGNUP -> {
                if (queryAccountPort.existsByPhoneNumber(phoneNumber)) {
                    throw DuplicatedPhoneNumberException()
                }
            }
            CheckPhoneNumberType.FIND_ACCOUNT -> {
                if (!queryAccountPort.existsByPhoneNumber(phoneNumber)) {
                    throw AccountNotFoundException()
                }
            }
        }
    }
}