package com.indistraw.account.adapter.output.persistence

import com.indistraw.account.adapter.output.persistence.common.converter.AccountConverter
import com.indistraw.account.adapter.output.persistence.repository.AccountRepository
import com.indistraw.account.application.port.output.CommandAccountPort
import com.indistraw.account.domain.Account
import org.springframework.stereotype.Component
import java.util.*

@Component
class CommandAccountPersistenceAdapter(
    private val accountRepository: AccountRepository,
    private val accountConverter: AccountConverter
): CommandAccountPort {

    override fun saveAccount(account: Account): UUID {
        val accountEntity = accountConverter toEntity account
        return accountRepository.save(accountEntity).accountIdx
    }

    override fun deleteAccount(account: Account) {
        val accountEntity = accountConverter toEntity account
        return accountRepository.delete(accountEntity)
    }

}