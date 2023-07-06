package com.project.indistraw.account.application.service

import com.project.indistraw.account.application.common.annotation.ServiceWithReadOnlyTransaction
import com.project.indistraw.account.application.exception.InvalidQRCodeUUIDException
import com.project.indistraw.account.application.port.input.CheckQRCodeUUIDUseCase
import com.project.indistraw.account.application.port.output.QueryQRCodeUUIDPort
import java.util.*

@ServiceWithReadOnlyTransaction
class CheckQRCodeUUIDService(
    private val queryQRCodeUUIDPort: QueryQRCodeUUIDPort
): CheckQRCodeUUIDUseCase {

    override fun execute(uuid: UUID) {
        queryQRCodeUUIDPort.findByUUID(uuid)
            ?: throw InvalidQRCodeUUIDException()
    }

}