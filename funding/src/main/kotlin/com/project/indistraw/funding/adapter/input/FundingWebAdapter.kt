package com.project.indistraw.funding.adapter.input

import com.project.indistraw.funding.adapter.input.converter.FundingDataConverter
import com.project.indistraw.funding.adapter.input.data.request.SaveFundingRequest
import com.project.indistraw.funding.application.port.input.SaveFundingUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/funding")
class FundingWebAdapter(
    private val fundingDataConverter: FundingDataConverter,
    private val saveFundingUseCase: SaveFundingUseCase
) {

    @PostMapping("crowdfunding/{crowdfundingIdx}/reword/{rewordIdx}")
    fun saveFunding(
        @PathVariable crowdfundingIdx: Long,
        @PathVariable rewordIdx: Long,
        @RequestBody request: SaveFundingRequest,
    ): ResponseEntity<Void> =
        saveFundingUseCase.execute(fundingDataConverter.toDto(crowdfundingIdx, rewordIdx, request))
            .let { ResponseEntity.status(HttpStatus.NO_CONTENT).build() }

}