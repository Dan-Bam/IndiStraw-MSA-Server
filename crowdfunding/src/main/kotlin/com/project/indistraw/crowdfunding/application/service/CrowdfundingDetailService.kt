package com.project.indistraw.crowdfunding.application.service

import com.project.indistraw.crowdfunding.application.common.annotation.ServiceWithReadOnlyTransaction
import com.project.indistraw.crowdfunding.application.exception.CrowdfundingNotFound
import com.project.indistraw.crowdfunding.application.port.input.CrowdfundingDetailUseCase
import com.project.indistraw.crowdfunding.application.port.input.dto.CrowdfundingDetailDto
import com.project.indistraw.crowdfunding.application.port.input.dto.RewordDto
import com.project.indistraw.crowdfunding.application.port.output.QueryCrowdfundingPort
import com.project.indistraw.crowdfunding.application.port.output.QueryRewordPort
import java.math.BigDecimal
import java.time.LocalDate

@ServiceWithReadOnlyTransaction
class CrowdfundingDetailService(
    private val queryCrowdfundingPort: QueryCrowdfundingPort,
    private val queryRewordPort: QueryRewordPort
): CrowdfundingDetailUseCase {

    override fun execute(idx: Long): CrowdfundingDetailDto {
        val crowdfunding = queryCrowdfundingPort.findByIdxOrNull(idx)
            ?: throw CrowdfundingNotFound()
        val reword = queryRewordPort.findByCrowdfundingIdx(crowdfunding.idx)

        return CrowdfundingDetailDto(
            title = crowdfunding.title,
            description = crowdfunding.description,
            writer = CrowdfundingDetailDto.Writer(
                accountIdx = crowdfunding.accountIdx,
                name = ""
            ),
            amount = CrowdfundingDetailDto.Amount(
                targetAmount = crowdfunding.amount.targetAmount,
                totalAmount = crowdfunding.amount.totalAmount,
                percentage = crowdfunding.amount.targetAmount.divide(crowdfunding.amount.totalAmount).multiply(BigDecimal.valueOf(100))
            ),
            remainingDay = LocalDate.now().minusDays(crowdfunding.date.endDate.dayOfMonth.toLong()).dayOfMonth,
            fundingCount = 0,
            reword = reword.map {
                RewordDto(
                    idx = it.idx,
                    title = it.title,
                    description = it.description,
                    price = it.price,
                    imageUrl = it.imageUrl
                )
            },
            thumbnailUrl = crowdfunding.thumbnailUrl,
            imageList = crowdfunding.imageList,
            detailList = crowdfunding.detailList
        )
    }

}