package com.project.indistraw.crowdfunding.application.port.output

import com.project.indistraw.crowdfunding.domain.Account

interface CommandAccountPort {

    fun saveAccount(account: Account)

}