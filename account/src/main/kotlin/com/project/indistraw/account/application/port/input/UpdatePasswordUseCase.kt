package com.project.indistraw.account.application.port.input

import com.project.indistraw.account.application.port.input.dto.UpdatePasswordDto

interface UpdatePasswordUseCase {

    fun execute(dto: UpdatePasswordDto)

}