package com.project.indistraw.crowdfunding.adapter.output.persistence

import com.project.indistraw.crowdfunding.adapter.output.persistence.common.converter.AccountConverter
import com.project.indistraw.crowdfunding.adapter.output.persistence.repository.AccountRepository
import com.project.indistraw.crowdfunding.application.port.output.QueryAccountPort
import com.project.indistraw.crowdfunding.domain.Account
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.*

@Component
class QueryAccountPersistenceAdapter(
    private val accountConverter: AccountConverter,
    private val accountRepository: AccountRepository
): QueryAccountPort {

    override fun findByAccountIdx(accountIdx: UUID): Account? {
        val accountEntity = accountRepository.findByIdOrNull(accountIdx)
        return accountConverter toDomain accountEntity
    }

}