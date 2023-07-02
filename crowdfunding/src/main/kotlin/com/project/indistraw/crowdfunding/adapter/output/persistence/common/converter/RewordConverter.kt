package com.project.indistraw.crowdfunding.adapter.output.persistence.common.converter

import com.project.indistraw.crowdfunding.adapter.output.persistence.entity.*
import com.project.indistraw.crowdfunding.adapter.output.persistence.repository.CrowdfundingRepository
import com.project.indistraw.crowdfunding.application.exception.CrowdfundingNotFoundException
import com.project.indistraw.crowdfunding.domain.Reward
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class RewordConverter(
    private val crowdfundingRepository: CrowdfundingRepository
) {

    infix fun toEntityList(domainList: List<Reward>): List<RewardEntity> {
        val crowdfundingEntity = crowdfundingRepository.findByIdOrNull(domainList[0].crowdfundingIdx)
            ?: throw CrowdfundingNotFoundException()
        return domainList.map {
            RewardEntity(
                idx = it.idx,
                title = it.title,
                description = it.description,
                price = it.price,
                imageUrl = it.imageUrl,
                crowdfundingEntity = crowdfundingEntity,
                totalCount = it.totalCount
            )
        }
    }

    infix fun toDomainList(entityList: List<RewardEntity>): List<Reward> {
        return entityList.map {
            Reward(
                idx = it.idx,
                crowdfundingIdx = it.crowdfundingEntity.idx,
                title = it.title,
                description = it.description,
                price = it.price,
                imageUrl = it.imageUrl,
                totalCount = it.totalCount
            )
        }
    }

}