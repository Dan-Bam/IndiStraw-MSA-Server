package com.project.indistraw.account.adapter.input.converter

import com.project.indistraw.account.adapter.input.data.request.QRCodeUUIDRequest
import com.project.indistraw.account.application.port.input.dto.QRCodeUUIDDto
import org.springframework.stereotype.Component

@Component
class QRCodeDataConverter {

    infix fun toDto(request: QRCodeUUIDRequest): QRCodeUUIDDto =
        QRCodeUUIDDto(
            uuid = request.uuid
        )

}