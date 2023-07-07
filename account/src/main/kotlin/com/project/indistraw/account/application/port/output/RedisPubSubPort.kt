package com.project.indistraw.account.application.port.output

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.util.*

interface RedisPubSubPort {

    fun createSseToRedisChanel(uuid: UUID): SseEmitter
    fun pingCheck(uuid: UUID)
    fun sendTokenToSse(uuid: UUID, accessToken: String)

}