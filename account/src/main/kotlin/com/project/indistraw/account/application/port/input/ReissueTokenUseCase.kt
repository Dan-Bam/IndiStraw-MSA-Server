package com.project.indistraw.account.application.port.input

import com.project.indistraw.account.application.port.output.dto.TokenDto

interface ReissueTokenUseCase {

    fun execute(refreshToken: String): TokenDto

}