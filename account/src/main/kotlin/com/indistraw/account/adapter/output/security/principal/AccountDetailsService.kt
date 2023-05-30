package com.indistraw.account.adapter.output.security.principal

import com.indistraw.account.adapter.output.persistence.repository.AccountRepository
import com.indistraw.account.application.common.annotation.ServiceWithReadOnlyTransaction
import com.indistraw.account.application.exception.AccountNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import java.util.*

@ServiceWithReadOnlyTransaction
class AccountDetailsService(
    private val accountRepository: AccountRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails =
        accountRepository.findByIdOrNull(UUID.fromString(username))
            .let { it ?: throw AccountNotFoundException() }
            .let { AccountDetails(it.accountIdx) }

}