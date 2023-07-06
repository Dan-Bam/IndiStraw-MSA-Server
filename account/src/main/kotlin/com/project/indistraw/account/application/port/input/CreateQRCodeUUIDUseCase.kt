package com.project.indistraw.account.application.port.input

import java.util.UUID

interface CreateQRCodeUUIDUseCase {

    fun execute(): UUID

}