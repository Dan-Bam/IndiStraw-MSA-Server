package com.project.indistraw.crowdfunding.application.service

import com.project.indistraw.crowdfunding.application.common.annotation.ServiceWithReadOnlyTransaction
import com.project.indistraw.crowdfunding.application.common.util.CalculateAmountUtil
import com.project.indistraw.crowdfunding.application.port.input.PopularCrowdfundingListUseCase
import com.project.indistraw.crowdfunding.application.port.input.dto.CrowdfundingListDto
import com.project.indistraw.crowdfunding.application.port.output.QueryCrowdfundingPort

@ServiceWithReadOnlyTransaction
class PopularCrowdfundingListService(
    private val queryCrowdfundingPort: QueryCrowdfundingPort,
    private val calculateAmountUtil: CalculateAmountUtil
): PopularCrowdfundingListUseCase {

    override fun execute(): List<CrowdfundingListDto> {
        val crowdfundingList = queryCrowdfundingPort.findTop5ByOrderByViewCount()
        return crowdfundingList.map {
            CrowdfundingListDto(
                idx = it.idx,
                title = it.title,
                description = it.description,
                percentage = calculateAmountUtil.calculateAmountPercentage(it.amount),
                thumbnailUrl = it.thumbnailUrl,
                statusType = it.statusType
            )
        }
    }

}