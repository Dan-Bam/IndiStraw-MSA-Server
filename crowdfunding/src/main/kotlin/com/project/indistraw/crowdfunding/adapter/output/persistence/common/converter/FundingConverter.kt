package com.project.indistraw.crowdfunding.adapter.output.persistence.common.converter

import com.project.indistraw.crowdfunding.adapter.output.persistence.common.GenericConverter
import com.project.indistraw.crowdfunding.adapter.output.persistence.entity.FundingEntity
import com.project.indistraw.crowdfunding.adapter.output.persistence.repository.AccountRepository
import com.project.indistraw.crowdfunding.adapter.output.persistence.repository.CrowdfundingRepository
import com.project.indistraw.crowdfunding.adapter.output.persistence.repository.RewardRepository
import com.project.indistraw.crowdfunding.application.exception.AccountNotFoundException
import com.project.indistraw.crowdfunding.application.exception.CrowdfundingNotFoundException
import com.project.indistraw.crowdfunding.application.exception.RewardNotFoundException
import com.project.indistraw.crowdfunding.domain.Funding
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class FundingConverter(
    private val crowdfundingRepository: CrowdfundingRepository,
    private val rewardRepository: RewardRepository,
    private val accountRepository: AccountRepository
): GenericConverter<Funding, FundingEntity> {

    override infix fun toEntity(domain: Funding): FundingEntity {
        val crowdfundingEntity = crowdfundingRepository.findByIdOrNull(domain.crowdfundingIdx) ?: throw CrowdfundingNotFoundException()
        val rewordEntity = rewardRepository.findByIdOrNull(domain.rewordIdx) ?: throw RewardNotFoundException()
        val orderer = accountRepository.findByIdOrNull(domain.ordererIdx) ?: throw AccountNotFoundException()
        return domain.let {
            FundingEntity(
                idx = it.idx,
                crowdfunding = crowdfundingEntity,
                reward = rewordEntity,
                orderer = orderer,
                price = it.price,
                extraPrice = it.extraPrice,
                activity = it.activity
            )
        }
    }

    override infix fun toDomain(entity: FundingEntity?): Funding? =
        entity?.let {
            Funding(
                idx = it.idx,
                crowdfundingIdx = it.crowdfunding.idx,
                rewordIdx = it.reward.idx,
                ordererIdx = it.orderer.accountIdx,
                price = it.price,
                extraPrice = it.extraPrice,
                activity = it.activity
            )
        }

}