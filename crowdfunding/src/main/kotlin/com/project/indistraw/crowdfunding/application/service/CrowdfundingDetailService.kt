package com.project.indistraw.crowdfunding.application.service

import com.project.indistraw.crowdfunding.application.common.annotation.ServiceWithTransaction
import com.project.indistraw.crowdfunding.application.common.util.CalculateAmountUtil
import com.project.indistraw.crowdfunding.application.exception.CrowdfundingNotFoundException
import com.project.indistraw.crowdfunding.application.port.input.CrowdfundingDetailUseCase
import com.project.indistraw.crowdfunding.application.port.input.dto.CrowdfundingDetailDto
import com.project.indistraw.crowdfunding.application.port.input.dto.RewardDto
import com.project.indistraw.crowdfunding.application.port.output.QueryAccountPort
import com.project.indistraw.crowdfunding.application.port.output.QueryCrowdfundingPort
import com.project.indistraw.crowdfunding.application.port.output.QueryRequestIpPort
import com.project.indistraw.crowdfunding.application.port.output.QueryRewardPort
import com.project.indistraw.crowdfunding.domain.Crowdfunding
import com.project.indistraw.global.event.QueryCrowdfundingEvent
import org.springframework.context.ApplicationEventPublisher
import java.time.LocalDate

@ServiceWithTransaction
class CrowdfundingDetailService(
    private val queryCrowdfundingPort: QueryCrowdfundingPort,
    private val queryRewardPort: QueryRewardPort,
    private val queryRequestIpPort: QueryRequestIpPort,
    private val queryAccountPort: QueryAccountPort,
    private val calculateAmountUtil: CalculateAmountUtil,
    private val publisher: ApplicationEventPublisher
) : CrowdfundingDetailUseCase {

    override fun execute(idx: Long): CrowdfundingDetailDto {
        val crowdfunding = queryCrowdfundingPort.findByIdxOrNull(idx)
            ?: throw CrowdfundingNotFoundException()
        val reward = queryRewardPort.findByCrowdfundingIdx(crowdfunding.idx)
        val writer = queryAccountPort.getAccountInfo()

        publishEvent(crowdfunding)

        return CrowdfundingDetailDto(
            title = crowdfunding.title,
            description = crowdfunding.description,
            writer = CrowdfundingDetailDto.Writer(
                idx = writer.accountIdx,
                name = writer.name
            ),
            amount = CrowdfundingDetailDto.Amount(
                targetAmount = crowdfunding.amount.targetAmount,
                totalAmount = crowdfunding.amount.totalAmount,
                percentage = calculateAmountUtil.calculateAmountPercentage(crowdfunding.amount)
            ),
            remainingDay = LocalDate.now().minusDays(crowdfunding.endDate.dayOfMonth.toLong()).dayOfMonth,
            fundingCount = 0,
            reward = reward.map {
                RewardDto(
                    idx = it.idx,
                    title = it.title,
                    description = it.description,
                    price = it.price,
                    imageUrl = it.imageUrl,
                    totalCount = it.totalCount
                )
            },
            statusType = crowdfunding.statusType,
            thumbnailUrl = crowdfunding.thumbnailUrl,
            imageList = crowdfunding.imageList,
            fileList = crowdfunding.fileList
        )
    }

    private fun publishEvent(crowdfunding: Crowdfunding) {
        val currentIp = queryRequestIpPort.getCurrentRequestIp()
        val queryCrowdfundingEvent = QueryCrowdfundingEvent(crowdfunding, currentIp)
        publisher.publishEvent(queryCrowdfundingEvent)
    }

}
