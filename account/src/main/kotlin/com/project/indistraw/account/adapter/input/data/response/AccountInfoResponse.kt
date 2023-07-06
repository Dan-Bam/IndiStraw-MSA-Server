package com.project.indistraw.account.adapter.input.data.response

import java.util.UUID

data class AccountInfoResponse(
    val accountIdx: UUID,
    val id: String,
    val name: String,
    val phoneNumber: String,
    val address: String?,
    val profileUrl: String?
)