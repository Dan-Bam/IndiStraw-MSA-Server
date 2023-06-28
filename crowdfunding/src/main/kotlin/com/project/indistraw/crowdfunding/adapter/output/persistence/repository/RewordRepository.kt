package com.project.indistraw.crowdfunding.adapter.output.persistence.repository

import com.project.indistraw.crowdfunding.adapter.output.persistence.entity.CrowdfundingEntity
import com.project.indistraw.crowdfunding.adapter.output.persistence.entity.RewordEntity
import org.springframework.data.repository.CrudRepository

interface RewordRepository: CrudRepository<RewordEntity, Long> {

    fun findByCrowdfundingEntity(crowdfundingEntity: CrowdfundingEntity): List<RewordEntity>

}