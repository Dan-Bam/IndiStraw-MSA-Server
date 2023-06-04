package com.project.indistraw.account.adapter.output.persistence.repository

import com.project.indistraw.account.adapter.output.persistence.entity.AuthenticationEntity
import org.springframework.data.repository.CrudRepository

interface AuthenticationRepository: CrudRepository<AuthenticationEntity, String>