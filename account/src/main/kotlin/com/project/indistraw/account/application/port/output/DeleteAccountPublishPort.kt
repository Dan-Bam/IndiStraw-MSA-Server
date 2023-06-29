package com.project.indistraw.account.application.port.output

import java.util.UUID

interface DeleteAccountPublishPort {

    fun execute(accountIdx: UUID)

}