package com.indistraw.account.adapter.output.persistence.common.converter

import com.indistraw.account.adapter.output.persistence.entity.AccountEntity
import com.indistraw.account.domain.Account
import com.indistraw.account.domain.Authority
import org.springframework.stereotype.Component

@Component
class AccountConverter {

    infix fun toEntity(domain: Account): AccountEntity =
        domain.let {
            AccountEntity(
                accountIdx = it.accountIdx,
                id = it.id,
                encodedPassword = it.encodedPassword,
                name = it.name,
                phoneNumber = it.phoneNumber,
                profileUrl = it.profileUrl,
                authority = Authority.ROLE_ACCOUNT
            )
        }

    infix fun toDomain(entity: AccountEntity?): Account? =
        entity?.let {
            Account(
                accountIdx = it.accountIdx,
                id = it.id,
                encodedPassword = it.encodedPassword,
                name = it.name,
                phoneNumber = it.phoneNumber,
                profileUrl = it.profileUrl,
                authority = it.authority
            )
        }

}