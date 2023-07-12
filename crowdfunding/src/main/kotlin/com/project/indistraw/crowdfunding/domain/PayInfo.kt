package com.project.indistraw.crowdfunding.domain

import java.util.UUID

data class PayInfo(
    val receiptId: String,
    val accountIdx: UUID
)