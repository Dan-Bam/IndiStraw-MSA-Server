package com.project.indistraw.account.adapter.output.persistence.common.converter

import com.project.indistraw.account.adapter.output.persistence.entity.AccountEntity
import com.project.indistraw.account.adapter.output.persistence.entity.Address
import com.project.indistraw.account.domain.Account
import com.project.indistraw.account.domain.Authority
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
                address = Address(zipcode = it.address.zipcode, it.address.streetAddress, it.address.detailAddress),
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
                address = com.project.indistraw.account.domain.Address(
                    zipcode = it.address.zipcode,
                    it.address.streetAddress,
                    it.address.detailAddress
                ),
                profileUrl = it.profileUrl,
                authority = it.authority
            )
        }

}