package com.project.indistraw.crowdfunding.adapter.output.persistence.repository

import com.project.indistraw.crowdfunding.adapter.output.persistence.entity.CrowdfundingEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface CrowdfundingRepository: JpaRepository<CrowdfundingEntity, Long> {

    fun findAllByTitleContainingOrDescriptionContaining(pageable: Pageable, title: String, description: String): Page<CrowdfundingEntity>

}