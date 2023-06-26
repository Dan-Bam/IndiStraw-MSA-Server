package com.project.indistraw.crowdfunding.adapter.output.persistence

import com.project.indistraw.crowdfunding.adapter.output.persistence.common.converter.CrowdfundingConverter
import com.project.indistraw.crowdfunding.adapter.output.persistence.repository.CrowdfundingRepository
import com.project.indistraw.crowdfunding.application.port.output.QueryCrowdfundingPort
import com.project.indistraw.crowdfunding.domain.Crowdfunding
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class QueryCrowdfundingPersistenceAdapter(
    private val crowdfundingConverter: CrowdfundingConverter,
    private val crowdfundingRepository: CrowdfundingRepository
): QueryCrowdfundingPort {

    override fun findByIdxOrNull(idx: Long): Crowdfunding? {
        val crowdfundingEntity = crowdfundingRepository.findByIdOrNull(idx)
        return crowdfundingConverter toDomain crowdfundingEntity
    }

    override fun findAll(pageRequest: PageRequest): Page<Crowdfunding> {
        val crowdfundingList = crowdfundingRepository.findAll(pageRequest)
        return crowdfundingList.map { crowdfundingConverter toDomain it }
    }

}