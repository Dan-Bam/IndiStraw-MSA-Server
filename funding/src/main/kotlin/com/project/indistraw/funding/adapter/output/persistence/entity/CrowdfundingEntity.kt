package com.project.indistraw.funding.adapter.output.persistence.entity

import com.project.indistraw.funding.adapter.output.persistence.common.entity.BaseIdxEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "crowdfunding")
class CrowdfundingEntity(
    @Column(name = "crowdfunding_idx", nullable = false)
    override val idx: Long,

    @Column(nullable = false)
    val title: String,

    @Column(nullable = false)
    val description: String,

    @Column(nullable = false)
    val status: String,

    @Column(nullable = false)
    val thumbnailUrl: String
): BaseIdxEntity(idx)