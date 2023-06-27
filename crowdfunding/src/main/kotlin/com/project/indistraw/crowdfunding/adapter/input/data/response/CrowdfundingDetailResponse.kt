package com.project.indistraw.crowdfunding.adapter.input.data.response

import com.project.indistraw.crowdfunding.application.port.input.dto.RewordDto
import java.math.BigDecimal
import java.util.Date
import java.util.UUID

data class CrowdfundingDetailResponse(
    val title: String,
    val description: String,
    val writer: Writer,
    val amount: Amount,
    val remainingDay: Date,
    val fundingCount: Long,
    val reword: List<RewordDto>,
    val thumbnailUrl: String,
    val imageList: List<String>,
    val detailList: List<String>
) {

    data class Writer(
        val accountIdx: UUID,
        val name: String
    )

    data class Amount(
        val targetAmount: BigDecimal,
        val totalAmount: BigDecimal,
        val percentage: BigDecimal
    )

}