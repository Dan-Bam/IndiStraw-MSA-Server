package com.project.indistraw.crowdfunding.adapter.output.persistence.repository

import com.project.indistraw.crowdfunding.adapter.output.persistence.entity.FundingEntity
import org.springframework.data.repository.CrudRepository

interface FundingRepository: CrudRepository<FundingEntity, String>