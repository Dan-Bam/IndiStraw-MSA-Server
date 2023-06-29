package com.project.indistraw.account.application.port.output

import com.project.indistraw.account.domain.Account

interface CreateAccountPublishPort {

    fun execute(account: Account)

}