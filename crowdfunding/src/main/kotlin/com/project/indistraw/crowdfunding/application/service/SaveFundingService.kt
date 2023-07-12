package com.project.indistraw.crowdfunding.application.service

import com.project.indistraw.crowdfunding.application.common.annotation.ServiceWithTransaction
import com.project.indistraw.crowdfunding.application.exception.ReceiptIdNotFoundException
import com.project.indistraw.crowdfunding.application.port.input.SaveFundingUseCase
import com.project.indistraw.crowdfunding.application.port.input.dto.SaveFundingDto
import com.project.indistraw.crowdfunding.application.port.output.*
import com.project.indistraw.crowdfunding.domain.Funding

@ServiceWithTransaction
class SaveFundingService(
    private val commandFundingPort: CommandFundingPort,
    private val accountSecurityPort: AccountSecurityPort,
    private val queryPayInfoPort: QueryPayInfoPort,
    private val payPort: PayPort
): SaveFundingUseCase {

    override fun execute(dto: SaveFundingDto) {
        if (!queryPayInfoPort.existByReceiptId(dto.receiptId)) {
            throw ReceiptIdNotFoundException()
        }
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
            activity = Funding.Activity.DEPOSITED
        )
        commandFundingPort.saveFunding(funding)
    }

}