package com.project.indistraw.account.application.port.input

interface SendAuthCodeUseCase {

    fun execute(phoneNumber: String)

}