package com.project.indistraw.crowdfunding.adapter.output.persistence.repository

import com.project.indistraw.crowdfunding.adapter.output.persistence.entity.CrowdfundingViewCountEntity
import org.springframework.data.repository.CrudRepository

interface CrowdfundingViewCountRepository: CrudRepository<CrowdfundingViewCountEntity, Long>