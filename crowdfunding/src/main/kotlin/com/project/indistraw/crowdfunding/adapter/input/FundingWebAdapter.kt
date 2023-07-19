package com.project.indistraw.crowdfunding.adapter.input

import com.project.indistraw.crowdfunding.adapter.input.converter.CrowdfundingDataConverter
import com.project.indistraw.crowdfunding.adapter.input.converter.FundingDataConverter
import com.project.indistraw.crowdfunding.adapter.input.data.request.SaveFundingRequest
import com.project.indistraw.crowdfunding.adapter.input.data.response.CrowdfundingListResponse
import com.project.indistraw.crowdfunding.application.port.input.CancelFundingUseCase
import com.project.indistraw.crowdfunding.application.port.input.CreatePayInfoUseCase
import com.project.indistraw.crowdfunding.application.port.input.FindMyFundingListUseCase
import com.project.indistraw.crowdfunding.application.port.input.SaveFundingUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1/funding")
class FundingWebAdapter(
    private val fundingDataConverter: FundingDataConverter,
    private val crowdfundingDataConverter: CrowdfundingDataConverter,
    private val createPayInfoUseCase: CreatePayInfoUseCase,
    private val saveFundingUseCase: SaveFundingUseCase,
    private val cancelFundingUseCase: CancelFundingUseCase,
    private val findMyFundingListUseCase: FindMyFundingListUseCase,
) {

    @PostMapping("receipt")
    fun createPayInfo(): ResponseEntity<Map<String, String>> =
        createPayInfoUseCase.execute()
            .let { ResponseEntity.ok(mapOf("receiptId" to it)) }

    @PostMapping("crowdfunding/{crowdfundingIdx}/reword/{rewordIdx}")
    fun saveFunding(
        @PathVariable crowdfundingIdx: Long,
        @PathVariable rewordIdx: Long,
        @RequestBody request: SaveFundingRequest,
    ): ResponseEntity<Void> =
        fundingDataConverter.toDto(crowdfundingIdx, rewordIdx, request)
            .let { saveFundingUseCase.execute(it) }
            .let { ResponseEntity.status(HttpStatus.NO_CONTENT).build() }

    @PostMapping("cancel/{idx}/receipt/{receiptId}")
    fun cancelPay(@PathVariable idx: Long, @PathVariable receiptId: String): ResponseEntity<Void> =
        cancelFundingUseCase.execute(idx, receiptId)
            .let { ResponseEntity.status(HttpStatus.NO_CONTENT).build() }

    @GetMapping("my")
    fun findOrderCrowdfunding(): ResponseEntity<List<CrowdfundingListResponse>> =
        findMyFundingListUseCase.execute()
            .let { crowdfundingDataConverter.toResponse(it) }
            .let { ResponseEntity.ok(it) }

}