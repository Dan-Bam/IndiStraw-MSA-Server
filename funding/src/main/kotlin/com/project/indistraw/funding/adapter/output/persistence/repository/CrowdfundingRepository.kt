package com.project.indistraw.funding.adapter.output.persistence.repository

import com.project.indistraw.funding.adapter.output.persistence.entity.CrowdfundingEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CrowdfundingRepository: JpaRepository<CrowdfundingEntity, Long>