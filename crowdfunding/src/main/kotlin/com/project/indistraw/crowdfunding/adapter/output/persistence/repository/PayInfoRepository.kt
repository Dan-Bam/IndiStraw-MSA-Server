package com.project.indistraw.crowdfunding.adapter.output.persistence.repository

import com.project.indistraw.crowdfunding.adapter.output.persistence.entity.PayInfoEntity
import org.springframework.data.repository.CrudRepository
import java.util.*

interface PayInfoRepository: CrudRepository<PayInfoEntity, UUID>