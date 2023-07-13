package com.project.indistraw.crowdfunding.adapter.output.persistence.repository

import com.project.indistraw.crowdfunding.adapter.output.persistence.entity.AccountEntity
import com.project.indistraw.crowdfunding.adapter.output.persistence.entity.CrowdfundingEntity
import com.project.indistraw.crowdfunding.adapter.output.persistence.entity.QAccountEntity.accountEntity
import com.project.indistraw.crowdfunding.adapter.output.persistence.entity.QCrowdfundingEntity.crowdfundingEntity
import com.project.indistraw.crowdfunding.adapter.output.persistence.entity.QFundingEntity.fundingEntity
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import java.util.*

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

    fun findOrdererByCrowdfundingIdx(crowdfundingIdx: Long): List<AccountEntity> {
        return queryFactory.selectFrom(accountEntity)
            .join(fundingEntity.crowdfunding, crowdfundingEntity)
            .where(fundingEntity.crowdfunding.idx.eq(crowdfundingIdx))
            .fetch()
    }

    fun findByTitleOrDescriptionContaining(keyword: String?): List<CrowdfundingEntity> {
        return queryFactory.selectFrom(crowdfundingEntity)
            .where(eqKeyword(keyword))
            .orderBy(crowdfundingEntity.createdAt.desc())
            .fetch()
    }

    private fun eqKeyword(keyword: String?): BooleanExpression? {
        if (keyword == null) return null
        return crowdfundingEntity.title.like("%$keyword$").or(crowdfundingEntity.title.like("%$keyword$"))
    }

}