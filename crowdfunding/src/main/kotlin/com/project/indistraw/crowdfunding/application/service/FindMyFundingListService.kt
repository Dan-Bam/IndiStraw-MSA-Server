package com.project.indistraw.crowdfunding.application.service

import com.project.indistraw.crowdfunding.application.common.annotation.ServiceWithReadOnlyTransaction
import com.project.indistraw.crowdfunding.application.common.util.CalculateAmountUtil
import com.project.indistraw.crowdfunding.application.port.input.FindMyFundingListUseCase
import com.project.indistraw.crowdfunding.application.port.input.dto.CrowdfundingListDto
import com.project.indistraw.crowdfunding.application.port.output.AccountSecurityPort
import com.project.indistraw.crowdfunding.application.port.output.QueryCrowdfundingPort

@ServiceWithReadOnlyTransaction
class FindMyFundingListService(
    private val queryCrowdfundingPort: QueryCrowdfundingPort,
    private val accountSecurityPort: AccountSecurityPort,
    private val calculateAmountUtil: CalculateAmountUtil
): FindMyFundingListUseCase {

    override fun execute(): List<CrowdfundingListDto> {
        val ordererIdx = accountSecurityPort.getCurrentAccountIdx()
        val crowdfundingList = queryCrowdfundingPort.findByOrdererIdx(ordererIdx)
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