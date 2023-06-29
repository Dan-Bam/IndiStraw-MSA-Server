package com.project.indistraw.account.application.port.output

import java.util.*

interface CreateAccountPublishPort {

    fun execute(accountIdx: UUID)

}