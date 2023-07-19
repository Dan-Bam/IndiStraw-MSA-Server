package com.project.indistraw.account.application.port.input

import com.project.indistraw.account.application.port.input.dto.SignInDto
import com.project.indistraw.account.application.port.output.dto.TokenDto

interface SignInUseCase {

    fun execute(dto: SignInDto): TokenDto

}