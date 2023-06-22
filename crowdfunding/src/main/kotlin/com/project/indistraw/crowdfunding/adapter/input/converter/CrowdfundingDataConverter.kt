package com.project.indistraw.crowdfunding.adapter.input.converter

import com.project.indistraw.crowdfunding.adapter.input.data.request.CreateCrowdfundingRequest
import com.project.indistraw.crowdfunding.application.port.input.dto.CreateCrowdfundingDto
import com.project.indistraw.crowdfunding.application.port.input.dto.DirectorAccountDto
import com.project.indistraw.crowdfunding.application.port.input.dto.RewordDto
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
                    title = it.title,
                    description = it.description,
                    price = it.price,
                    imageUrl = it.imageUrl
                )
            },
            endDate = dto.endDate,
            thumbnailUrl = dto.thumbnailUrl,
            imageList = dto.imageList,
            detailList = dto.detailList
        )

}