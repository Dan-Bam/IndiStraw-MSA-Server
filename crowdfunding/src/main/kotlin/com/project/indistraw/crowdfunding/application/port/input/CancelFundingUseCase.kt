package com.project.indistraw.crowdfunding.application.port.input

interface CancelFundingUseCase {

    fun execute(fundingIdx: Long, receiptId: String)

}