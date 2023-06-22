package com.project.indistraw.crowdfunding.adapter.output.persistence.entity

import com.project.indistraw.crowdfunding.adapter.output.persistence.common.entity.BaseIdxEntity
import com.project.indistraw.crowdfunding.domain.Activity
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "crowdfunding")
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

    @Embedded
    val directorAccount: DirectorAccountEntity,

    @Column(nullable = false)
    val date: DateEntity,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val activity: Activity,

    @Column(nullable = false)
    val thumbnailUrl: String,

    @ElementCollection
    @CollectionTable(name = "crowdfunding_image", joinColumns = [JoinColumn(name = "crowdfunding_idx")])
    @Column(name = "image_url", columnDefinition = "TEXT", nullable = false)
    val imageList: List<String>,

    @ElementCollection
    @CollectionTable(name = "crowdfunding_detail", joinColumns = [JoinColumn(name = "crowdfunding_idx")])
    @Column(name = "detail_url", columnDefinition = "TEXT", nullable = false)
    val detailList: List<String>,
): BaseIdxEntity(idx)