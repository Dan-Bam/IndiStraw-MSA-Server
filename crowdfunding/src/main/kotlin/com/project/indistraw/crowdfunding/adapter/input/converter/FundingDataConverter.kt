package com.project.indistraw.crowdfunding.adapter.input.converter

import com.project.indistraw.crowdfunding.adapter.input.data.request.SaveFundingRequest
import com.project.indistraw.crowdfunding.application.port.input.dto.SaveFundingDto
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