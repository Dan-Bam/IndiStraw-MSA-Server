package com.project.indistraw.crowdfunding.adapter.input.converter

import com.project.indistraw.crowdfunding.adapter.input.data.request.CreateCrowdfundingRequest
import com.project.indistraw.crowdfunding.adapter.input.data.response.*
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
            reward = dto.reward.map {
                RewardDto(
                    idx = 0L,
                    title = it.title,
                    description = it.description,
                    price = it.price,
                    totalCount = it.totalCount,
                    imageList = it.imageList
                )
            },
            endDate = dto.endDate,
            thumbnailUrl = dto.thumbnailUrl,
            imageList = dto.imageList,
            fileList = dto.fileList
        )

    infix fun toResponse(dto: CrowdfundingDetailDto): CrowdfundingDetailResponse =
        CrowdfundingDetailResponse(
            title = dto.title,
            description = dto.description,
            thumbnailUrl = dto.thumbnailUrl,
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
            imageList = dto.imageList,
            fileList = dto.fileList,
            reward = dto.reward.map {
                RewardResponse(
                    idx = it.idx,
                    title = it.title,
                    description = it.description,
                    price = it.price,
                    imageList = it.imageList,
                    totalCount = it.totalCount
                )
            },
            isFunding = dto.isFunding,
            status = dto.statusType
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

    fun toResponse(dto: MyCrowdfundingDetailDto): MyCrowdfundingDetailResponse =
        MyCrowdfundingDetailResponse(
            title = dto.title,
            thumbnailUrl = dto.thumbnailUrl,
            amount = MyCrowdfundingDetailResponse.Amount(
                targetAmount = dto.amount.targetAmount,
                totalAmount = dto.amount.totalAmount,
                percentage = dto.amount.percentage
            ),
            remainingDay = dto.remainingDay,
            fundingCount = dto.fundingCount,
            reward = dto.reward.map {
                RewardResponse(
                    idx = it.idx,
                    title = it.title,
                    description = it.description,
                    price = it.price,
                    imageList = it.imageList,
                    totalCount = it.totalCount
                )
            },
            ordererList = dto.orderer.map {
                MyCrowdfundingDetailResponse.Orderer(
                    accountIdx = it.accountIdx,
                    name = it.name,
                    phoneNumber = it.phoneNumber,
                    zipcode = it.zipcode,
                    address = it.address,
                    profileUrl = it.profileUrl
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
