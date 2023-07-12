package com.project.indistraw.crowdfunding.adapter.output.persistence.repository

import com.project.indistraw.crowdfunding.adapter.output.persistence.entity.PayInfoEntity
import org.springframework.data.repository.CrudRepository

interface PayInfoRepository: CrudRepository<PayInfoEntity, String>