package com.project.indistraw.account.application.port.output

import com.project.indistraw.account.domain.Account
import java.util.*

interface CommandAccountPort {

    fun saveAccount(account: Account): UUID
    fun deleteAccount(account: Account)

}