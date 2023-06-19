package com.project.indistraw.account.application.port.output

import java.util.*

interface AccountSecurityPort {

    fun getCurrentAccountIdx(): UUID

}