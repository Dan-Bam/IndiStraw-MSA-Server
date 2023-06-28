package com.project.indistraw.account.application.port.input

import com.project.indistraw.account.application.port.input.dto.UpdateAccountProfileDto

interface UpdateAccountInfoUseCase {

    fun execute(dto: UpdateAccountProfileDto)

}