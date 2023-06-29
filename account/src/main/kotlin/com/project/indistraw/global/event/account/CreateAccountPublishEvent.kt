package com.project.indistraw.global.event.account

import com.project.indistraw.account.domain.Account

data class CreateAccountPublishEvent(
    val account: Account
)