package com.project.indistraw.account.adapter.output.persistence.repository

import com.project.indistraw.account.adapter.output.persistence.entity.RefreshTokenEntity
import org.springframework.data.repository.CrudRepository

interface RefreshTokenRepository: CrudRepository<RefreshTokenEntity, String>