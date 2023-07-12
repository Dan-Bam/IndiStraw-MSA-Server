package com.project.indistraw.account.adapter.output.persistence

import com.project.indistraw.account.adapter.output.persistence.common.converter.AccountConverter
import com.project.indistraw.account.adapter.output.persistence.repository.AccountRepository
import com.project.indistraw.account.application.port.output.CommandAccountPort
import com.project.indistraw.account.domain.Account
import org.springframework.stereotype.Component

@Component
class CommandAccountPersistenceAdapter(
    private val accountRepository: AccountRepository,
    private val accountConverter: AccountConverter
): CommandAccountPort {

    override fun saveAccount(account: Account): Account {
        val accountEntity = accountConverter toEntity account
        return (accountConverter toDomain accountRepository.save(accountEntity))!!
    }

    override fun deleteAccount(account: Account) {
        val accountEntity = accountConverter toEntity account
        return accountRepository.delete(accountEntity)
    }

}