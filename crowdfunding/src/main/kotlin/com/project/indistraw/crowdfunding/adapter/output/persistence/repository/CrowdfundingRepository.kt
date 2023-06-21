package com.project.indistraw.crowdfunding.adapter.output.persistence.repository

import com.project.indistraw.crowdfunding.adapter.output.persistence.entity.CrowdfundingEntity
import org.springframework.data.repository.CrudRepository

interface CrowdfundingRepository: CrudRepository<CrowdfundingEntity, Long>