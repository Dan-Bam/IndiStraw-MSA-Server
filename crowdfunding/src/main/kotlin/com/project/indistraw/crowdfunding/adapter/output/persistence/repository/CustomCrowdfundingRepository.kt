package com.project.indistraw.crowdfunding.adapter.output.persistence.repository

import com.project.indistraw.crowdfunding.adapter.output.persistence.entity.CrowdfundingEntity
import com.querydsl.jpa.impl.JPAQueryFactory
import com.project.indistraw.crowdfunding.adapter.output.persistence.entity.QFundingEntity.fundingEntity
import com.project.indistraw.crowdfunding.adapter.output.persistence.entity.QCrowdfundingEntity.crowdfundingEntity
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class CustomCrowdfundingRepository(
    private val queryFactory: JPAQueryFactory
) {

    fun findByOrdererIdx(ordererIdx: UUID): List<CrowdfundingEntity> {
        return queryFactory.selectFrom(crowdfundingEntity)
            .join(fundingEntity.crowdfunding, crowdfundingEntity)
            .where(fundingEntity.orderer.accountIdx.eq(ordererIdx))
            .fetch()
    }

}