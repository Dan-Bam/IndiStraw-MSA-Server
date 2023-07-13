package com.project.indistraw.crowdfunding.adapter.output.persistence

import com.project.indistraw.crowdfunding.adapter.output.persistence.common.converter.FundingConverter
import com.project.indistraw.crowdfunding.adapter.output.persistence.repository.CrowdfundingRepository
import com.project.indistraw.crowdfunding.adapter.output.persistence.repository.FundingRepository
import com.project.indistraw.crowdfunding.application.exception.CrowdfundingNotFoundException
import com.project.indistraw.crowdfunding.application.port.output.QueryFundingPort
import com.project.indistraw.crowdfunding.domain.Funding
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.*

@Component
class QueryFundingPersistenceAdapter(
    private val fundingConverter: FundingConverter,
    private val crowdfundingRepository: CrowdfundingRepository,
    private val fundingRepository: FundingRepository
): QueryFundingPort {

    override fun findByIdx(idx: Long): Funding? {
        val fundingEntity = fundingRepository.findByIdOrNull(idx)
        return fundingConverter toDomain fundingEntity
    }

    override fun existByOrdererIdx(ordererIdx: UUID): Boolean {
        return fundingRepository.existsByOrdererAccountIdx(ordererIdx)
    }

    override fun countByCrowdfundingIdx(crowdfundingIdx: Long): Long {
        val crowdfunding = crowdfundingRepository.findByIdOrNull(crowdfundingIdx)
            ?: throw CrowdfundingNotFoundException()
        return fundingRepository.countByCrowdfunding(crowdfunding)
    }

}