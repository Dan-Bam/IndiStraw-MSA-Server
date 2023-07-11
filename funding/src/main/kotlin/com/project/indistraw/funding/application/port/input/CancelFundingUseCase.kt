package com.project.indistraw.funding.application.port.input

interface CancelFundingUseCase {

    fun execute(fundingIdx: Long, receiptId: String)

}