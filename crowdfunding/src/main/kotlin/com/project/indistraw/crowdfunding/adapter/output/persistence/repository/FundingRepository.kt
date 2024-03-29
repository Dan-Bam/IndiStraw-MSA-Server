package com.project.indistraw.crowdfunding.adapter.output.persistence.repository

import com.project.indistraw.crowdfunding.adapter.output.persistence.entity.CrowdfundingEntity
import com.project.indistraw.crowdfunding.adapter.output.persistence.entity.FundingEntity
import org.springframework.data.repository.CrudRepository
import java.util.*

interface FundingRepository: CrudRepository<FundingEntity, Long> {

    fun existsByOrdererAccountIdxAndCrowdfundingIdx(accountIdx: UUID, crowdfundingIdx: Long): Boolean
    fun countByCrowdfunding(crowdfunding: CrowdfundingEntity): Long

}