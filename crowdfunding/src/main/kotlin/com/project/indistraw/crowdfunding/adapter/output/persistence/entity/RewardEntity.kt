package com.project.indistraw.crowdfunding.adapter.output.persistence.entity

import com.project.indistraw.crowdfunding.adapter.output.persistence.common.entity.BaseIdxEntity
import org.hibernate.annotations.Cascade
import org.hibernate.annotations.CascadeType
import javax.persistence.*

@Entity
@Table(name = "reward")
class RewardEntity(
    @Column(name = "reward_idx", nullable = false)
    override val idx: Long,

    @Column(nullable = false)
    val title: String,

    @Column(nullable = false)
    val description: String,

    @Column(nullable = false)
    val price: Long,

    @Column(columnDefinition = "TEXT", nullable = false)
    val imageUrl: String,

    @Column(nullable = true)
    val totalCount: Long?,

    @Cascade(CascadeType.DELETE)
    @ManyToOne(fetch = FetchType.LAZY)
    val crowdfundingEntity: CrowdfundingEntity
): BaseIdxEntity(idx)