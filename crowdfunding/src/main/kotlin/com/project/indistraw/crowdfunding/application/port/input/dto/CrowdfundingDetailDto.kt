package com.project.indistraw.crowdfunding.application.port.input.dto

import com.project.indistraw.crowdfunding.domain.StatusType
import java.math.BigDecimal
import java.util.*

data class CrowdfundingDetailDto(
    val title: String,
    val description: String,
    val writer: Writer,
    val amount: Amount,
    val remainingDay: Int,
    val fundingCount: Long,
    val reword: List<RewordDto>,
    val statusType: StatusType,
    val thumbnailUrl: String,
    val imageList: List<String>,
    val detailList: List<String>
) {

    data class Writer(
        val idx: UUID,
        val name: String
    )

    data class Amount(
        val targetAmount: BigDecimal,
        val totalAmount: BigDecimal,
        val percentage: BigDecimal
    )

}
