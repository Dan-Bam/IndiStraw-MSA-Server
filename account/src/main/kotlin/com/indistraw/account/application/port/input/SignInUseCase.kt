package com.indistraw.account.application.port.input

import com.indistraw.account.application.port.input.dto.SignInDto
import com.indistraw.account.application.port.output.dto.TokenDto

interface SignInUseCase {

    fun execute(dto: SignInDto): TokenDto

}