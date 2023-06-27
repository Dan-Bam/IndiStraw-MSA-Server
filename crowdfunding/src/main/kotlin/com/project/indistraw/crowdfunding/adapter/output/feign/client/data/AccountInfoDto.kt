package com.project.indistraw.crowdfunding.adapter.output.feign.client.data

import java.util.UUID

data class AccountInfoDto(
    val idx: UUID,
    val name: String,
    val profileUrl: String?
)
