package com.project.indistraw.crowdfunding.application.service

import com.project.indistraw.crowdfunding.application.common.annotation.ServiceWithReadOnlyTransaction
import com.project.indistraw.crowdfunding.application.common.util.CalculateAmountUtil
import com.project.indistraw.crowdfunding.application.port.input.dto.CrowdfundingListDto
import com.project.indistraw.crowdfunding.application.port.output.QueryCrowdfundingPort
import com.project.indistraw.crowdfunding.application.port.output.SearchCrowdfundingUseCase

@ServiceWithReadOnlyTransaction
class SearchCrowdfundingService(
    private val queryCrowdfundingPort: QueryCrowdfundingPort,
    private val calculateAmountUtil: CalculateAmountUtil
): SearchCrowdfundingUseCase {

    override fun execute(keyword: String?): List<CrowdfundingListDto> {
        val crowdfundingList = queryCrowdfundingPort.findByTitleOrDescriptionContaining(keyword)

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