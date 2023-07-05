package com.project.indistraw.account.application.port.input

import com.project.indistraw.account.domain.QRCodeUUID
import java.util.UUID

interface QueryQRCodeUUIDPort {

    fun findByUUID(uuid: UUID): QRCodeUUID?

}