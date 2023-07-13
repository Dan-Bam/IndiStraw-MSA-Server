package com.project.indistraw.crowdfunding.application.port.output

import com.project.indistraw.crowdfunding.domain.Account
import java.util.*

interface QueryAccountPort {

    fun findByAccountIdx(accountIdx: UUID): Account?

}