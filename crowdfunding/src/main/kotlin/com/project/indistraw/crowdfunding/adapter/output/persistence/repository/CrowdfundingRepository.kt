package com.project.indistraw.crowdfunding.adapter.output.persistence.repository

import com.project.indistraw.crowdfunding.adapter.output.persistence.entity.CrowdfundingEntity
import com.project.indistraw.crowdfunding.domain.StatusType
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CrowdfundingRepository: JpaRepository<CrowdfundingEntity, Long> {

    fun findTop5ByStatusTypeNotOrderByViewCountDesc(statusType: StatusType): List<CrowdfundingEntity>
    fun findByWriterIdx(writerIdx: UUID): List<CrowdfundingEntity>
    fun findAllByStatusTypeNot(statusType: StatusType, pageable: Pageable): Page<CrowdfundingEntity>

}