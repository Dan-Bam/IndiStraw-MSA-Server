package com.project.indistraw.account.application.service

import com.project.indistraw.account.application.port.input.CheckSsePingUseCase
import com.project.indistraw.account.application.port.output.RedisPubSubPort
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CheckSsePingService(
    private val redisPubSubPort: RedisPubSubPort
): CheckSsePingUseCase {

    override fun execute(uuid: UUID) {
        redisPubSubPort.pingCheck(uuid)
    }

}