package com.project.indistraw.crowdfunding.application.service

import com.project.indistraw.crowdfunding.application.common.annotation.ServiceWithTransaction
import com.project.indistraw.crowdfunding.application.exception.CrowdfundingNotFoundException
import com.project.indistraw.crowdfunding.application.exception.RewardNotFoundException
import com.project.indistraw.crowdfunding.application.port.input.SaveFundingUseCase
import com.project.indistraw.crowdfunding.application.port.input.dto.SaveFundingDto
import com.project.indistraw.crowdfunding.application.port.output.*
import com.project.indistraw.crowdfunding.domain.Funding

@ServiceWithTransaction
class SaveFundingService(
    private val commandFundingPort: CommandFundingPort,
    private val accountSecurityPort: AccountSecurityPort,
    private val queryCrowdfundingPort: QueryCrowdfundingPort,
    private val queryRewardPort: QueryRewardPort,
    private val commandRewardPort: CommandRewardPort,
    private val commandCrowdfundingPort: CommandCrowdfundingPort,
    private val payPort: PayPort
): SaveFundingUseCase {

    override fun execute(dto: SaveFundingDto) {
        // receiptId를 가지고 bootpay 결제 정보 검증 요청을 합니다.
        payPort.confirm(dto.receiptId)

        val ordererIdx = accountSecurityPort.getCurrentAccountIdx()
        var crowdfunding = queryCrowdfundingPort.findByIdxOrNull(dto.crowdfundingIdx)
            ?: throw CrowdfundingNotFoundException()
        var reward = queryRewardPort.findByIdx(dto.rewordIdx)
            ?: throw RewardNotFoundException()
        val funding = Funding(
            idx = 0L,
            crowdfundingIdx = dto.crowdfundingIdx,
            rewordIdx = dto.rewordIdx,
            ordererIdx = ordererIdx,
            price = dto.price,
            extraPrice = dto.extraPrice,
            activity = Funding.Activity.DEPOSITED
        )
        commandFundingPort.saveFunding(funding)

        if (dto.extraPrice == null) {
            crowdfunding = crowdfunding.increaseTotalAmount(dto.price)
        }
        crowdfunding = crowdfunding.increaseTotalAmount(dto.price.plus(dto.extraPrice!!))
        reward = reward.deductionTotalCount()

        commandCrowdfundingPort.saveCrowdfunding(crowdfunding)
        commandRewardPort.saveReward(reward)
    }

}