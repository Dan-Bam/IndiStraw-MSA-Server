package com.project.indistraw.funding.application.service

import com.project.indistraw.funding.application.common.annotation.ServiceWithTransaction
import com.project.indistraw.funding.application.port.input.SaveFundingUseCase
import com.project.indistraw.funding.application.port.input.dto.SaveFundingDto
import com.project.indistraw.funding.application.port.output.AccountSecurityPort
import com.project.indistraw.funding.application.port.output.CommandFundingPort
import com.project.indistraw.funding.application.port.output.PayPort
import com.project.indistraw.funding.domain.Activity
import com.project.indistraw.funding.domain.Funding

@ServiceWithTransaction
class SaveFundingService(
    private val commandFundingPort: CommandFundingPort,
    private val accountSecurityPort: AccountSecurityPort,
    private val payPort: PayPort
): SaveFundingUseCase {

    override fun execute(dto: SaveFundingDto) {
        // receiptId를 가지고 bootpay 결제 정보 검증 요청을 합니다.
        payPort.confirm(dto.receiptId)

        val ordererIdx = accountSecurityPort.getCurrentAccountIdx()
        val funding = Funding(
            idx = 0L,
            crowdfundingIdx = dto.crowdfundingIdx,
            rewordIdx = dto.rewordIdx,
            ordererIdx = ordererIdx,
            price = dto.price,
            extraPrice = dto.extraPrice,
            activity = Activity.DEPOSITED
        )
        commandFundingPort.saveFunding(funding)
    }

}