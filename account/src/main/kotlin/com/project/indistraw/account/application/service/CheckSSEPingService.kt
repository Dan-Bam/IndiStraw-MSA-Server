package com.project.indistraw.account.application.service

import com.project.indistraw.account.application.port.input.CheckSSEPingUseCase
import com.project.indistraw.account.application.port.output.RedisPubSubPort
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CheckSSEPingService(
    private val redisPubSubPort: RedisPubSubPort
): CheckSSEPingUseCase {

    override fun execute(uuid: UUID) {
        redisPubSubPort.pingCheck(uuid)
    }

}