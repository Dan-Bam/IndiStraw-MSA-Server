package com.project.indistraw.account.application.port.input

import java.util.UUID

interface CheckQRCodeUUIDUseCase {

    fun execute(uuid: UUID)

}