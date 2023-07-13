package com.project.indistraw.crowdfunding.adapter.output.persistence

import com.project.indistraw.crowdfunding.adapter.output.persistence.common.converter.AccountConverter
import com.project.indistraw.crowdfunding.adapter.output.persistence.common.converter.FundingConverter
import com.project.indistraw.crowdfunding.adapter.output.persistence.repository.CrowdfundingRepository
import com.project.indistraw.crowdfunding.adapter.output.persistence.repository.CustomCrowdfundingRepository
import com.project.indistraw.crowdfunding.adapter.output.persistence.repository.FundingRepository
import com.project.indistraw.crowdfunding.application.exception.CrowdfundingNotFoundException
import com.project.indistraw.crowdfunding.application.port.output.QueryFundingPort
import com.project.indistraw.crowdfunding.domain.Account
import com.project.indistraw.crowdfunding.domain.Funding
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.*

@Component
class QueryFundingPersistenceAdapter(
    private val fundingConverter: FundingConverter,
    private val accountConverter: AccountConverter,
    private val crowdfundingRepository: CrowdfundingRepository,
    private val fundingRepository: FundingRepository,
    private val customCrowdfundingRepository: CustomCrowdfundingRepository
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

    override fun findOrdererByCrowdfundingIdx(crowdfundingIdx: Long): List<Account> {
        val orderer = customCrowdfundingRepository.findOrdererByCrowdfundingIdx(crowdfundingIdx)
        return orderer.map { (accountConverter toDomain it)!! }
    }

}