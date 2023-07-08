package com.project.indistraw.account.application.port.input

import com.project.indistraw.account.application.port.input.dto.QRCodeUUIDDto

interface QRCodeSignInUseCase {

    fun execute(dto: QRCodeUUIDDto)

}