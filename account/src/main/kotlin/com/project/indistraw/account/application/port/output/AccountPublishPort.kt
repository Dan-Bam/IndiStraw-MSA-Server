package com.project.indistraw.account.application.port.output

import com.project.indistraw.account.domain.Account
import java.util.*

interface AccountPublishPort {

    fun create(account: Account)
    fun delete(accountIdx: UUID)

}