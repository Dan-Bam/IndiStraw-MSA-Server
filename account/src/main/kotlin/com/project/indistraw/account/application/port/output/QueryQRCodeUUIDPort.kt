package com.project.indistraw.account.application.port.output

import com.project.indistraw.account.domain.QRCodeUUID
import java.util.UUID

interface QueryQRCodeUUIDPort {

    fun findByUUID(uuid: UUID): QRCodeUUID?
    fun existByUUID(uuid: UUID): Boolean

}