package com.project.indistraw.account.application.port.output

import com.project.indistraw.account.domain.Account

interface CommandAccountPort {

    fun saveAccount(account: Account): Account
    fun deleteAccount(account: Account)

}