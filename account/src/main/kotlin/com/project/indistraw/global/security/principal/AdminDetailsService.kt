package com.project.indistraw.global.security.principal

import com.project.indistraw.account.adapter.output.persistence.repository.AccountRepository
import com.project.indistraw.account.application.common.annotation.ServiceWithTransaction
import com.project.indistraw.account.application.exception.AccountNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import java.util.*

@ServiceWithTransaction
class AdminDetailsService(
    private val accountRepository: AccountRepository
): UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails =
        accountRepository.findByIdOrNull(UUID.fromString(username))
            .let { it ?: throw AccountNotFoundException() }
            .let { AdminDetails(it.accountIdx) }

}