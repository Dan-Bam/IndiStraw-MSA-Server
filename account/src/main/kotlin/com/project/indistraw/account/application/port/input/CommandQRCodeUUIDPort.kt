package com.project.indistraw.account.application.port.input

import com.project.indistraw.account.domain.QRCodeUUID

interface CommandQRCodeUUIDPort {

    fun saveQRCodeUUID(domain: QRCodeUUID)

}