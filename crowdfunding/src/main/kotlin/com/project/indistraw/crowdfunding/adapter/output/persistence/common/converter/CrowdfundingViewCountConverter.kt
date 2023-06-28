package com.project.indistraw.crowdfunding.adapter.output.persistence.common.converter

import com.project.indistraw.crowdfunding.adapter.output.persistence.entity.CrowdfundingViewCountEntity
import com.project.indistraw.crowdfunding.domain.CrowdfundingViewCount
import org.springframework.stereotype.Component

@Component
class CrowdfundingViewCountConverter {

    infix fun toEntity(domain: CrowdfundingViewCount): CrowdfundingViewCountEntity =
        domain.let {
            CrowdfundingViewCountEntity(
                crowdfundingIdx = it.crowdfundingIdx,
                ips = it.ips
            )
        }

    infix fun toDomain(entity: CrowdfundingViewCountEntity): CrowdfundingViewCount =
        entity.let {
            CrowdfundingViewCount(
                crowdfundingIdx = it.crowdfundingIdx,
                ips = it.ips
            )
        }

}