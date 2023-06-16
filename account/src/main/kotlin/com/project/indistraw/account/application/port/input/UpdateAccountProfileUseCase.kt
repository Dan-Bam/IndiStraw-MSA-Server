package com.project.indistraw.account.application.port.input

import com.project.indistraw.account.application.port.input.dto.UpdateAccountProfileDto

interface UpdateAccountProfileUseCase {

    fun execute(dto: UpdateAccountProfileDto)

}