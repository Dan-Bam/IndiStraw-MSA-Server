package com.project.indistraw.account.application.port.input

import com.project.indistraw.account.application.port.input.dto.UpdateAddressDto

interface UpdateAddressUseCase {

    fun execute(updateAddressDto: UpdateAddressDto)

}