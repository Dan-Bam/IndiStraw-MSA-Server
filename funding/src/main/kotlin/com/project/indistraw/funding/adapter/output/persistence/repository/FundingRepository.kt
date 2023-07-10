package com.project.indistraw.funding.adapter.output.persistence.repository

import com.project.indistraw.funding.adapter.output.persistence.entity.FundingEntity
import org.springframework.data.repository.CrudRepository

interface FundingRepository: CrudRepository<FundingEntity, Long>