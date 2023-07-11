package com.project.indistraw.funding.adapter.input.converter

import com.project.indistraw.funding.adapter.input.data.request.SaveFundingRequest
import com.project.indistraw.funding.application.port.input.dto.SaveFundingDto
import org.springframework.stereotype.Component

@Component
class FundingDataConverter {

    fun toDto(crowdfundingIdx: Long, rewordIdx: Long, request: SaveFundingRequest): SaveFundingDto =
        SaveFundingDto(
            crowdfundingIdx = crowdfundingIdx,
            rewordIdx = rewordIdx,
            receiptId = request.receiptId,
            price = request.price,
            extraPrice = request.extraPrice
        )

}