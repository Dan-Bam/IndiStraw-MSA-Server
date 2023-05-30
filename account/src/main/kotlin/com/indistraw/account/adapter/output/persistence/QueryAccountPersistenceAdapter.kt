package com.indistraw.account.adapter.output.persistence

import com.indistraw.account.adapter.output.persistence.common.converter.AccountConverter
import com.indistraw.account.adapter.output.persistence.repository.AccountRepository
import com.indistraw.account.application.port.output.QueryAccountPort
import com.indistraw.account.domain.Account
import org.springframework.stereotype.Component

@Component
class QueryAccountPersistenceAdapter(
    private val accountRepository: AccountRepository,
    private val accountConverter: AccountConverter
): QueryAccountPort {

    override fun findByIdOrNull(id: String): Account? {
        val accountEntity = accountRepository.findById(id)
        return accountConverter toDomain accountEntity
    }

}