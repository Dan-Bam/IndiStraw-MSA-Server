package com.project.indistraw.account.application.port.output

import com.project.indistraw.account.domain.Account

interface QueryAccountPort {

    fun existsById(id: String): Boolean
    fun existsByPhoneNumber(phoneNumber: String): Boolean
    fun findByIdOrNull(id: String): Account?

}