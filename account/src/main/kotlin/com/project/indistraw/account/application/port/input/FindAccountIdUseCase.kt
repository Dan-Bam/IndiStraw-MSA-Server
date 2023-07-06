package com.project.indistraw.account.application.port.input

interface FindAccountIdUseCase {

    fun execute(phoneNumber: String): String

}