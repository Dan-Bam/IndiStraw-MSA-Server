package com.project.indistraw.crowdfunding.adapter.output.persistence

import com.project.indistraw.crowdfunding.adapter.output.persistence.repository.FundingRepository
import com.project.indistraw.crowdfunding.application.port.output.QueryPayInfoPort
import org.springframework.stereotype.Component

@Component
class QueryFundingPersistenceAdapter(
    private val fundingRepository: FundingRepository
): QueryPayInfoPort {

    override fun existByReceiptId(receiptId: String): Boolean {
        return fundingRepository.existsById(receiptId)
    }

}