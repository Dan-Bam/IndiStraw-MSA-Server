package com.project.indistraw.account.application.port.output

import java.util.*

interface RedisPubSubPort {

    fun createSseToRedisChanel(uuid: UUID)
    fun pingCheck(uuid: UUID)
    fun sendTokenToSse(uuid: UUID, accessToken: String)

}