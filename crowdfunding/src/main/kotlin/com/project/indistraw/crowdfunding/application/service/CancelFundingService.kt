package com.project.indistraw.crowdfunding.application.service

import com.project.indistraw.crowdfunding.application.common.annotation.ServiceWithTransaction
import com.project.indistraw.crowdfunding.application.exception.FundingNotFoundException
import com.project.indistraw.crowdfunding.application.exception.ReceiptIdNotFoundException
import com.project.indistraw.crowdfunding.application.port.input.CancelFundingUseCase
import com.project.indistraw.crowdfunding.application.port.output.CommandFundingPort
import com.project.indistraw.crowdfunding.application.port.output.PayPort
import com.project.indistraw.crowdfunding.application.port.output.QueryFundingPort
import com.project.indistraw.crowdfunding.application.port.output.QueryPayInfoPort

@ServiceWithTransaction
class CancelFundingService(
    private val queryFundingPort: QueryFundingPort,
    private val commandFundingPort: CommandFundingPort,
    private val queryPayInfoPort: QueryPayInfoPort,
    private val payPort: PayPort
): CancelFundingUseCase {

    override fun execute(fundingIdx: Long, receiptId: String) {
        if (!queryPayInfoPort.existByReceiptId(receiptId)) {
            throw ReceiptIdNotFoundException()
        }
        val funding = queryFundingPort.findByIdx(fundingIdx)
            ?: throw FundingNotFoundException()
        // receiptId를 가지고 bootpay 결제 취소 요청을 합니다.
        payPort.cancel(receiptId)
        commandFundingPort.deleteFunding(funding)
    }

}