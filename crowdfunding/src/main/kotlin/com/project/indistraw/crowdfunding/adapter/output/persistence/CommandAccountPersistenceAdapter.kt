package com.project.indistraw.crowdfunding.adapter.output.persistence

import com.project.indistraw.crowdfunding.adapter.output.persistence.common.converter.AccountConverter
import com.project.indistraw.crowdfunding.adapter.output.persistence.repository.AccountRepository
import com.project.indistraw.crowdfunding.application.port.output.CommandAccountPort
import com.project.indistraw.crowdfunding.domain.Account
import org.springframework.stereotype.Component

@Component
class CommandAccountPersistenceAdapter(
    private val accountConverter: AccountConverter,
    private val accountRepository: AccountRepository
): CommandAccountPort {

    override fun saveAccount(account: Account) {
        val accountEntity = accountConverter toEntity account
        accountRepository.save(accountEntity)
    }

}