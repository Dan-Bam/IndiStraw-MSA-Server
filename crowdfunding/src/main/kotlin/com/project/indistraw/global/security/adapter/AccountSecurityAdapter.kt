package com.project.indistraw.global.security.adapter

import com.project.indistraw.crowdfunding.application.port.output.AccountSecurityPort
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.util.*

@Component
class AccountSecurityAdapter: AccountSecurityPort {

    override fun getCurrentAccountIdx(): UUID =
        UUID.fromString(SecurityContextHolder.getContext().authentication.name)

}