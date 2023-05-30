package com.indistraw.account.application.port.output

import com.indistraw.account.domain.Account

interface QueryAccountPort {

    fun findByIdOrNull(id: String): Account?

}