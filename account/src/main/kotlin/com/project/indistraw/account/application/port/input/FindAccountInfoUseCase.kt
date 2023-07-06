package com.project.indistraw.account.application.port.input

import com.project.indistraw.account.application.port.input.dto.AccountInfoDto

interface FindAccountInfoUseCase {

    fun execute(): AccountInfoDto

}