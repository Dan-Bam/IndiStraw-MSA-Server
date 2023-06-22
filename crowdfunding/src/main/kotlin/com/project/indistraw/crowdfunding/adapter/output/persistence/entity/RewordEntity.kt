package com.project.indistraw.crowdfunding.adapter.output.persistence.entity

import com.project.indistraw.crowdfunding.adapter.output.persistence.common.entity.BaseIdxEntity
import org.hibernate.annotations.Cascade
import org.hibernate.annotations.CascadeType
import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "reword")
class RewordEntity(
    @Column(name = "reword_idx", nullable = false)
    override val idx: Long,

    @Column(nullable = false)
    val title: String,

    @Column(nullable = false)
    val description: String,

    @Column(nullable = false)
    val price: BigDecimal,

    @Column(columnDefinition = "TEXT", nullable = false)
    val imageUrl: String,

    @Cascade(CascadeType.DELETE)
    @ManyToOne(fetch = FetchType.LAZY)
    val crowdfundingEntity: CrowdfundingEntity
): BaseIdxEntity(idx)