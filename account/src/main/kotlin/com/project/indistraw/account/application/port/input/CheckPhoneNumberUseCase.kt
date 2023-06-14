package com.project.indistraw.account.application.port.input

import com.project.indistraw.account.application.common.enums.CheckPhoneNumberType

interface CheckPhoneNumberUseCase {

    fun execute(phoneNumber: String, type: CheckPhoneNumberType)

}