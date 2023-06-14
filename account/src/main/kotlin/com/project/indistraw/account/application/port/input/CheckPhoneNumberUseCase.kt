package com.project.indistraw.account.application.port.input

interface CheckPhoneNumberUseCase {

    fun execute(phoneNumber: String, type: String)

}