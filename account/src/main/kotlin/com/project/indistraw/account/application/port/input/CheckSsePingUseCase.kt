package com.project.indistraw.account.application.port.input

import java.util.UUID

interface CheckSsePingUseCase {

    fun execute(uuid: UUID)

}