package com.indistraw.account.application.port.input

import com.indistraw.account.application.port.input.dto.SignUpDto
import java.util.*

interface SignUpUseCase {

    fun execute(dto: SignUpDto): UUID

}