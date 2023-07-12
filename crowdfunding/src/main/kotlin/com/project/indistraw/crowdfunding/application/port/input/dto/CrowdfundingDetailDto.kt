package com.project.indistraw.crowdfunding.application.port.input.dto

import com.project.indistraw.crowdfunding.domain.StatusType
import java.util.*

data class CrowdfundingDetailDto(
    val title: String,
    val description: String,
    val thumbnailUrl: String,
    val writer: Writer,
    val amount: AmountDto,
    val remainingDay: Long,
    val fundingCount: Long,
    val imageList: List<String>,
    val fileList: List<String>,
    val reward: List<RewardDto>,
    val statusType: StatusType
) {

    data class Writer(
        val idx: UUID,
        val name: String
    )

}
