package com.project.indistraw.crowdfunding.adapter.output.persistence

import com.project.indistraw.crowdfunding.adapter.output.persistence.common.converter.RewordConverter
import com.project.indistraw.crowdfunding.adapter.output.persistence.repository.CrowdfundingRepository
import com.project.indistraw.crowdfunding.adapter.output.persistence.repository.RewordRepository
import com.project.indistraw.crowdfunding.application.exception.CrowdfundingNotFoundException
import com.project.indistraw.crowdfunding.application.port.output.QueryRewordPort
import com.project.indistraw.crowdfunding.domain.Reword
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class QueryRewordPersistenceAdapter(
    private val rewordConverter: RewordConverter,
    private val crowdfundingRepository: CrowdfundingRepository,
    private val rewordRepository: RewordRepository
): QueryRewordPort {

    override fun findByCrowdfundingIdx(crowdfundingIdx: Long): List<Reword> {
        val crowdfundingEntity = crowdfundingRepository.findByIdOrNull(crowdfundingIdx)
            ?: throw CrowdfundingNotFoundException()
        val rewordList = rewordRepository.findByCrowdfundingEntity(crowdfundingEntity)
        return rewordConverter toDomainList rewordList
    }

}