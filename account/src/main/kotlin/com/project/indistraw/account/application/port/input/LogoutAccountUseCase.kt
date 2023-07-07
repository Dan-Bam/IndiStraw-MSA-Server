package com.project.indistraw.account.application.port.input

interface LogoutAccountUseCase {

    fun execute(refreshToken: String)

}