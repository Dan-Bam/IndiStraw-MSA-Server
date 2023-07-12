package com.project.indistraw.account.application.port.output

import com.project.indistraw.account.application.port.output.dto.TokenDto
import com.project.indistraw.account.domain.Account
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.util.*

interface SseEmitterPort {

    fun createSse(uuid: UUID): SseEmitter
    fun sendTokenToSse(uuid: UUID, account: Account): TokenDto

}