package com.project.indistraw.account.application.port.input

import com.project.indistraw.account.application.port.input.dto.AccountProfileDetailDto

interface AccountProfileDetailUseCase {

    fun execute(): AccountProfileDetailDto

}