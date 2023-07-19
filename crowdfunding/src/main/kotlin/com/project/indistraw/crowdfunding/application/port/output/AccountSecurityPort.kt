package com.project.indistraw.crowdfunding.application.port.output

import java.util.UUID

interface AccountSecurityPort {

    fun getCurrentAccountIdx(): UUID

}