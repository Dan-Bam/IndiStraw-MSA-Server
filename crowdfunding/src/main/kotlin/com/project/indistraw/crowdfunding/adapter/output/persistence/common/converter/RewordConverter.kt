package com.project.indistraw.crowdfunding.adapter.output.persistence.common.converter

import com.project.indistraw.crowdfunding.adapter.output.persistence.entity.*
import com.project.indistraw.crowdfunding.adapter.output.persistence.repository.CrowdfundingRepository
import com.project.indistraw.crowdfunding.application.exception.CrowdfundingNotFoundException
import com.project.indistraw.crowdfunding.domain.Reword
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class RewordConverter(
    private val crowdfundingRepository: CrowdfundingRepository
) {

    infix fun toEntityList(domainList: List<Reword>): List<RewordEntity> {
        val crowdfundingEntity = crowdfundingRepository.findByIdOrNull(domainList[0].crowdfundingIdx)
            ?: throw CrowdfundingNotFoundException()
        return domainList.map {
            RewordEntity(
                idx = 0,
                title = it.title,
                description = it.description,
                price = it.price,
                imageUrl = it.imageUrl,
                crowdfundingEntity = crowdfundingEntity
            )
        }
    }

    infix fun toDomainList(entityList: List<RewordEntity>): List<Reword> {
        return entityList.map {
            Reword(
                idx = 0,
                title = it.title,
                description = it.description,
                price = it.price,
                imageUrl = it.imageUrl,
                crowdfundingIdx = it.crowdfundingEntity.idx
            )
        }
    }

}