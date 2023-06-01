package com.project.indistraw.account.adapter.output.persistence

import com.project.indistraw.account.adapter.output.persistence.common.converter.AccountConverter
import com.project.indistraw.account.adapter.output.persistence.repository.AccountRepository
import com.project.indistraw.account.application.port.output.QueryAccountPort
import com.project.indistraw.account.domain.Account
import org.springframework.stereotype.Component

@Component
class QueryAccountPersistenceAdapter(
    private val accountRepository: AccountRepository,
    private val accountConverter: AccountConverter
) : QueryAccountPort {

    override fun existsById(id: String): Boolean =
        accountRepository.existsById(id)

    override fun existsByPhoneNumber(phoneNumber: String): Boolean =
        accountRepository.existsByPhoneNumber(phoneNumber)

    override fun findByIdOrNull(id: String): Account? {
        val accountEntity = accountRepository.findById(id)
        return accountConverter toDomain accountEntity
    }

}