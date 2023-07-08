package com.project.indistraw.account.application.port.input

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.util.UUID

interface ConnectSseUseCase {

    fun execute(uuid: UUID): SseEmitter

}