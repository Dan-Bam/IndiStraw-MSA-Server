package com.project.indistraw.account.application.service

import com.project.indistraw.account.application.common.annotation.ServiceWithReadOnlyTransaction
import com.project.indistraw.account.application.exception.InvalidQRCodeUUIDException
import com.project.indistraw.account.application.port.input.CheckQRCodeUUIDUseCase
import com.project.indistraw.account.application.port.output.QueryQRCodeUUIDPort
import com.project.indistraw.account.application.port.output.RedisPubSubPort
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.util.*

@ServiceWithReadOnlyTransaction
class CheckQRCodeUUIDService(
    private val queryQRCodeUUIDPort: QueryQRCodeUUIDPort,
    private val redisPubSubPort: RedisPubSubPort
): CheckQRCodeUUIDUseCase {

    override fun execute(uuid: UUID) {
        queryQRCodeUUIDPort.findByUUID(uuid)
            ?: throw InvalidQRCodeUUIDException()
        redisPubSubPort.sendTokenToSse(uuid, getAccessToken())
    }

    private fun getAccessToken(): String {
        val requestContextHolder = RequestContextHolder.getRequestAttributes() as ServletRequestAttributes
        val httpServletRequest = requestContextHolder.request
        return httpServletRequest.getHeader("Authorization")
    }

}