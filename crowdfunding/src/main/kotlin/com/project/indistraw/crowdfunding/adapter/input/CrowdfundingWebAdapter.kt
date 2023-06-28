package com.project.indistraw.crowdfunding.adapter.input

import com.project.indistraw.crowdfunding.adapter.input.converter.CrowdfundingDataConverter
import com.project.indistraw.crowdfunding.adapter.input.data.request.CreateCrowdfundingRequest
import com.project.indistraw.crowdfunding.adapter.input.data.response.CrowdfundingDetailResponse
import com.project.indistraw.crowdfunding.adapter.input.data.response.CrowdfundingListResponse
import com.project.indistraw.crowdfunding.adapter.input.data.response.CrowdfundingPagingResponse
import com.project.indistraw.crowdfunding.application.port.input.CreateCrowdfundingUseCase
import com.project.indistraw.crowdfunding.application.port.input.CrowdfundingDetailUseCase
import com.project.indistraw.crowdfunding.application.port.input.CrowdfundingListUseCase
import com.project.indistraw.crowdfunding.application.port.input.PopularCrowdfundingListUseCase
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/crowdfunding")
class CrowdfundingWebAdapter(
    private val crowdfundingDataConverter: CrowdfundingDataConverter,
    private val createCrowdfundingUseCase: CreateCrowdfundingUseCase,
    private val crowdfundingDetailUseCase: CrowdfundingDetailUseCase,
    private val crowdfundingListUseCase: CrowdfundingListUseCase,
    private val popularCrowdfundingListUseCase: PopularCrowdfundingListUseCase
) {

    @PostMapping
    fun createCrowdfunding(@RequestBody request: CreateCrowdfundingRequest): ResponseEntity<Void> =
        createCrowdfundingUseCase.execute(crowdfundingDataConverter toDto request)
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }

    @GetMapping("/{idx}")
    fun createCrowdfunding(@PathVariable idx: Long): ResponseEntity<CrowdfundingDetailResponse> =
        crowdfundingDetailUseCase.execute(idx)
            .let { crowdfundingDataConverter.toResponse(it) }
            .let { ResponseEntity.ok(it) }

    @GetMapping("/list")
    fun crowdfundingList(@PageableDefault(size = 5, page = 0) pageable: Pageable): ResponseEntity<CrowdfundingPagingResponse> =
        crowdfundingListUseCase.execute(pageable)
            .let { crowdfundingDataConverter.toResponse(it) }
            .let { ResponseEntity.ok(it) }

    @GetMapping("/popular/list")
    fun popularCrowdfundingList(): ResponseEntity<List<CrowdfundingListResponse>> =
        popularCrowdfundingListUseCase.execute()
            .let { crowdfundingDataConverter.toResponse(it) }
            .let { ResponseEntity.ok(it) }


}