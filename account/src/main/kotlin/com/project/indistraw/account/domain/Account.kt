package com.project.indistraw.account.domain

import java.util.UUID

data class Account(
    val accountIdx: UUID,
    val id: String,
    val encodedPassword: String,
    var name: String,
    val phoneNumber: String,
    val address: Address,
    val profileUrl: String?,
    val authority: Authority
)