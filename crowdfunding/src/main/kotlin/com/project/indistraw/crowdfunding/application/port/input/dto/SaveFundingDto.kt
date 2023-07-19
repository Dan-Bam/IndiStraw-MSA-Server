package com.project.indistraw.crowdfunding.application.port.input.dto

data class SaveFundingDto(
    val crowdfundingIdx: Long,
    val rewordIdx: Long,
    val receiptId: String,
    val price: Long,
    val extraPrice: Long?
)