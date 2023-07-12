package com.project.indistraw.crowdfunding.adapter.output.persistence.entity

import com.project.indistraw.crowdfunding.adapter.output.persistence.common.entity.BaseIdxEntity
import com.project.indistraw.crowdfunding.domain.Funding
import org.hibernate.annotations.SQLDelete
import javax.persistence.*

@Entity
@Table(name = "funding")
@SQLDelete(sql = "update funding set deleted_at = CURRENT_TIMESTAMP where funding_idx = ?")
class FundingEntity(
    @Column(name = "funding_idx", nullable = false)
    override val idx: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crowdfunding_idx")
    val crowdfunding: CrowdfundingEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reward_idx")
    val reward: RewardEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_idx")
    val orderer: AccountEntity,

    @Column(nullable = false)
    val price: Long,

    @Column(nullable = true)
    val extraPrice: Long?,

    @Column(nullable = false)
    val activity: Funding.Activity
): BaseIdxEntity(idx)