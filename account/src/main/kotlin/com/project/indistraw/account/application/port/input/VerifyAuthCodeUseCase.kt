package com.project.indistraw.account.application.port.input

interface VerifyAuthCodeUseCase {

    fun execute(authCode: Int, phoneNumber: String)

}