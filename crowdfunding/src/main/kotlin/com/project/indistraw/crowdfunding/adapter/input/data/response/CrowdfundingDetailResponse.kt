package com.project.indistraw.crowdfunding.adapter.input.data.response

import com.project.indistraw.crowdfunding.domain.StatusType
import java.util.*

data class CrowdfundingDetailResponse(
    val title: String,
    val description: String,
    val writer: Writer,
    val amount: Amount,
    val remainingDay: Long,
    val fundingCount: Long,
    val reward: List<RewardResponse>,
    val status: StatusType,
    val thumbnailUrl: String,
    val imageList: List<String>,
    val fileList: List<String>
) {

    data class Writer(
        val idx: UUID,
        val name: String
    )

    data class Amount(
        val targetAmount: Long,
        val totalAmount: Long,
        val percentage: Double
    )

}
