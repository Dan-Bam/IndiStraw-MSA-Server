package com.project.indistraw.funding.application.port.output

import java.util.UUID

interface AccountSecurityPort {

    fun getCurrentAccountIdx(): UUID

}