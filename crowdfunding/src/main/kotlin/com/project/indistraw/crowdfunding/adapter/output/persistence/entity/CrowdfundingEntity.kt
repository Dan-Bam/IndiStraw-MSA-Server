package com.project.indistraw.crowdfunding.adapter.output.persistence.entity

import com.project.indistraw.crowdfunding.adapter.output.persistence.common.entity.BaseIdxEntity
import com.project.indistraw.crowdfunding.domain.Activity
import java.util.*
import javax.persistence.*

@Entity
class CrowdfundingEntity(
    @Column(name = "crowdfunding_idx", nullable = false)
    override val idx: Long,

    @Column(columnDefinition = "BINARY(16)", nullable = false)
    val accountIdx: UUID,

    @Column(nullable = false)
    val title: String,

    @Column(nullable = false)
    val description: String,

    @Embedded
    val amount: AmountEntity,

    @Column(nullable = false)
    val directorAccount: String,

    @Column(nullable = false)
    val thumbnailUrl: String,

    @Column(nullable = false)
    val date: DateEntity,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val activity: Activity,
): BaseIdxEntity(idx)