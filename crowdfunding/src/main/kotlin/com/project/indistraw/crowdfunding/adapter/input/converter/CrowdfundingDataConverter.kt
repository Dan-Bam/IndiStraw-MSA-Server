package com.project.indistraw.crowdfunding.adapter.input.converter

import com.project.indistraw.crowdfunding.adapter.input.data.request.CreateCrowdfundingRequest
import com.project.indistraw.crowdfunding.adapter.input.data.response.CrowdfundingDetailResponse
import com.project.indistraw.crowdfunding.adapter.input.data.response.CrowdfundingListResponse
import com.project.indistraw.crowdfunding.adapter.input.data.response.CrowdfundingPagingResponse
import com.project.indistraw.crowdfunding.application.port.input.dto.*
import org.springframework.stereotype.Component

@Component
class CrowdfundingDataConverter {

    infix fun toDto(dto: CreateCrowdfundingRequest): CreateCrowdfundingDto =
        CreateCrowdfundingDto(
            title = dto.title,
            description = dto.description,
            targetAmount = dto.targetAmount,
            directorAccount = DirectorAccountDto(
                bank = dto.directorAccount.bank,
                account = dto.directorAccount.account
            ),
            reword = dto.reword.map {
                RewordDto(
                    idx = 0L,
                    title = it.title,
                    description = it.description,
                    price = it.price,
                    imageUrl = it.imageUrl,
                    totalCount = it.totalCount
                )
            },
            endDate = dto.endDate,
            thumbnailUrl = dto.thumbnailUrl,
            imageList = dto.imageList,
            detailList = dto.detailList
        )

    infix fun toResponse(dto: CrowdfundingDetailDto): CrowdfundingDetailResponse =
        CrowdfundingDetailResponse(
            title = dto.title,
            description = dto.description,
            writer = CrowdfundingDetailResponse.Writer(
                idx = dto.writer.idx,
                name = dto.writer.name
            ),
            amount = CrowdfundingDetailResponse.Amount(
                targetAmount = dto.amount.targetAmount,
                totalAmount = dto.amount.totalAmount,
                percentage = dto.amount.percentage
            ),
            remainingDay = dto.remainingDay,
            fundingCount = dto.fundingCount,
            reword = dto.reword.map {
                RewordDto(
                    idx = it.idx,
                    title = it.title,
                    description = it.description,
                    price = it.price,
                    imageUrl = it.imageUrl,
                    totalCount = it.totalCount
                )
            },
            status = dto.statusType,
            thumbnailUrl = dto.thumbnailUrl,
            imageList = dto.imageList,
            detailList = dto.detailList
        )

    fun toResponse(dto: CrowdfundingPagingDto): CrowdfundingPagingResponse =
        CrowdfundingPagingResponse(
            isLast = dto.isLast,
            list = dto.list.map {
                CrowdfundingListResponse(
                    idx = it.idx,
                    title = it.title,
                    description = it.description,
                    percentage = it.percentage,
                    thumbnailUrl = it.thumbnailUrl,
                    status = it.statusType
                )
            }
        )

    fun toResponse(dto: List<CrowdfundingListDto>): List<CrowdfundingListResponse> =
        dto.map {
            CrowdfundingListResponse(
                idx = it.idx,
                title = it.title,
                description = it.description,
                percentage = it.percentage,
                thumbnailUrl = it.thumbnailUrl,
                status = it.statusType
            )
        }

}
