package com.project.indistraw.crowdfunding.application.service

import com.project.indistraw.crowdfunding.application.common.annotation.ServiceWithReadOnlyTransaction
import com.project.indistraw.crowdfunding.application.port.input.CrowdfundingListUseCase
import com.project.indistraw.crowdfunding.application.port.input.dto.CrowdfundingListDto
import com.project.indistraw.crowdfunding.application.port.input.dto.CrowdfundingPagingDto
import com.project.indistraw.crowdfunding.application.port.output.QueryCrowdfundingPort
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import java.math.BigDecimal

@ServiceWithReadOnlyTransaction
class CrowdfundingListService(
    private val queryCrowdfundingPort: QueryCrowdfundingPort
): CrowdfundingListUseCase {

    override fun execute(pageable: Pageable): CrowdfundingPagingDto {
        val pageRequest = PageRequest.of(pageable.pageNumber, pageable.pageSize, Sort.by(Sort.Order.desc("createdAt")))
        val crowdfundingList = queryCrowdfundingPort.findAll(pageRequest)

        val crowdfundingListDto = crowdfundingList.map {
            CrowdfundingListDto(
                idx = it.idx,
                title = it.title,
                description = it.description,
                percentage = it.amount.targetAmount.divide(it.amount.totalAmount).multiply(BigDecimal.valueOf(100)),
                thumbnailUrl = it.thumbnailUrl,
                activity = it.activity
            )
        }.toList()

        return CrowdfundingPagingDto(
            pageSize = pageable.pageNumber,
            isLast = crowdfundingList.isLast,
            list = crowdfundingListDto
        )
    }

}