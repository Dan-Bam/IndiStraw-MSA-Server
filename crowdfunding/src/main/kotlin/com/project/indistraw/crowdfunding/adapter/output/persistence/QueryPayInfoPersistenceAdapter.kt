package com.project.indistraw.crowdfunding.adapter.output.persistence

import com.project.indistraw.crowdfunding.adapter.output.persistence.repository.PayInfoRepository
import com.project.indistraw.crowdfunding.application.port.output.QueryPayInfoPort
import org.springframework.stereotype.Component

@Component
class QueryPayInfoPersistenceAdapter(
    private val payInfoRepository: PayInfoRepository
): QueryPayInfoPort {

    override fun existByReceiptId(receiptId: String): Boolean {
        return payInfoRepository.existsById(receiptId)
    }

}