package com.project.indistraw.funding.adapter.output.persistence.entity

import com.project.indistraw.funding.adapter.output.persistence.common.entity.BaseIdxEntity
import com.project.indistraw.funding.domain.Activity
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "funding")
class FundingEntity(
    @Column(name = "funding_idx", nullable = false)
    override val idx: Long,

    @Column(nullable = false)
    val crowdfundingIdx: Long,

    @Column(nullable = false)
    val rewordIdx: Long,

    @Column(nullable = false, columnDefinition = "BINARY(16)")
    val ordererIdx: UUID,

    @Column(nullable = false)
    val price: Long,

    @Column(nullable = true)
    val extraPrice: Long?,

    @Column(nullable = false)
    val activity: Activity
): BaseIdxEntity(idx)