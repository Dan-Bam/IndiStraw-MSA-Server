package com.project.indistraw.funding.application.service

import com.project.indistraw.funding.application.common.annotation.ServiceWithTransaction
import com.project.indistraw.funding.application.exception.FundingNotFoundException
import com.project.indistraw.funding.application.port.input.CancelFundingUseCase
import com.project.indistraw.funding.application.port.output.CommandFundingPort
import com.project.indistraw.funding.application.port.output.PayPort
import com.project.indistraw.funding.application.port.output.QueryFundingPort

@ServiceWithTransaction
class CancelFundingService(
    private val queryFundingPort: QueryFundingPort,
    private val commandFundingPort: CommandFundingPort,
    private val payPort: PayPort
): CancelFundingUseCase {

    override fun execute(fundingIdx: Long, receiptId: String) {
        val funding = queryFundingPort.findByIdx(fundingIdx)
            ?: throw FundingNotFoundException()
        // receiptId를 가지고 bootpay 결제 취소 요청을 합니다.
        payPort.cancel(receiptId)
        commandFundingPort.deleteFunding(funding)
    }

}