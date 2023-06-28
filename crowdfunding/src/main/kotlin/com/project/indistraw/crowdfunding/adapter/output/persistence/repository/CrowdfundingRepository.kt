package com.project.indistraw.crowdfunding.adapter.output.persistence.repository

import com.project.indistraw.crowdfunding.adapter.output.persistence.entity.CrowdfundingEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CrowdfundingRepository: JpaRepository<CrowdfundingEntity, Long> {

    fun findTop5ByOrderByViewCountDesc(): List<CrowdfundingEntity>

}