package com.project.indistraw.funding.adapter.output.persistence.common.converter

import com.project.indistraw.funding.adapter.output.persistence.entity.CrowdfundingEntity
import com.project.indistraw.funding.domain.Crowdfunding
import org.springframework.stereotype.Component

@Component
class CrowdfundingConverter {

    infix fun toEntity(domain: Crowdfunding): CrowdfundingEntity =
        domain.let {
            CrowdfundingEntity(
                idx = it.idx,
                title = it.title,
                description = it.description,
                thumbnailUrl = it.thumbnailUrl,
                status = it.status
            )
        }

}