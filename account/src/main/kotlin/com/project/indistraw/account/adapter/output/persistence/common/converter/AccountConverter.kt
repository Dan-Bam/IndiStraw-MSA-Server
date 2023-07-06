package com.project.indistraw.account.adapter.output.persistence.common.converter

import com.project.indistraw.account.adapter.output.persistence.common.GenericConverter
import com.project.indistraw.account.adapter.output.persistence.entity.AccountEntity
import com.project.indistraw.account.adapter.output.persistence.entity.AddressEntity
import com.project.indistraw.account.domain.Account
import com.project.indistraw.account.domain.Address
import com.project.indistraw.account.domain.Authority
import org.springframework.stereotype.Component

@Component
class AccountConverter: GenericConverter<Account, AccountEntity> {

    override infix fun toEntity(domain: Account): AccountEntity =
        domain.let {
            AccountEntity(
                accountIdx = it.accountIdx,
                id = it.id,
                encodedPassword = it.encodedPassword,
                name = it.name,
                phoneNumber = it.phoneNumber,
                address = AddressEntity(
                    zipcode = it.address?.zipcode,
                    streetAddress = it.address?.streetAddress,
                    detailAddress = it.address?.detailAddress
                ),
                profileUrl = it.profileUrl,
                authority = Authority.ROLE_ACCOUNT
            )
        }

    override infix fun toDomain(entity: AccountEntity?): Account? =
        entity?.let {
            Account(
                accountIdx = it.accountIdx,
                id = it.id,
                encodedPassword = it.encodedPassword,
                name = it.name,
                address = Address(
                    zipcode = entity.address?.zipcode,
                    streetAddress = entity.address?.streetAddress,
                    detailAddress = entity.address?.detailAddress
                ),
                phoneNumber = it.phoneNumber,
                profileUrl = it.profileUrl,
                authority = it.authority
            )
        }

}