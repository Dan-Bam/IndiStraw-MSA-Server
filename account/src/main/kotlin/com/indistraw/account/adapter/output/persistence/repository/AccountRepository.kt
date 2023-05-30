package com.indistraw.account.adapter.output.persistence.repository

import com.indistraw.account.adapter.output.persistence.entity.AccountEntity
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface AccountRepository: CrudRepository<AccountEntity, UUID> {

    fun findById(id: String): AccountEntity?

}