package com.project.indistraw.crowdfunding.application.port.input.dto

import java.util.UUID

data class SaveFundingDto(
    val crowdfundingIdx: Long,
    val rewordIdx: Long,
    val receiptId: UUID,
    val price: Long,
    val extraPrice: Long?
)