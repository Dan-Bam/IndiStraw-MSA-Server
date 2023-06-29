package com.project.indistraw.crowdfunding.adapter.output.feign.client.data

import java.util.UUID

data class AccountInfoDto(
    val accountIdx: UUID,
    val name: String,
    val profileUrl: String?
)
