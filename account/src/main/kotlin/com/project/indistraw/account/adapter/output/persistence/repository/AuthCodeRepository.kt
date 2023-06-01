package com.project.indistraw.account.adapter.output.persistence.repository

import com.project.indistraw.account.adapter.output.persistence.entity.AuthCodeEntity
import org.springframework.data.repository.CrudRepository
import java.util.*

interface AuthCodeRepository: CrudRepository<AuthCodeEntity, UUID>