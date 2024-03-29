package com.project.indistraw.crowdfunding.adapter.output.persistence.common.converter

import com.project.indistraw.crowdfunding.adapter.output.persistence.common.GenericConverter
import com.project.indistraw.crowdfunding.adapter.output.persistence.entity.AccountEntity
import com.project.indistraw.crowdfunding.adapter.output.persistence.entity.AddressEntity
import com.project.indistraw.crowdfunding.domain.Account
import org.springframework.stereotype.Component

@Component
class AccountConverter : GenericConverter<Account, AccountEntity> {

    override fun toEntity(domain: Account): AccountEntity =
        domain.let {
            AccountEntity(
                accountIdx = it.accountIdx,
                id = it.id,
                name = it.name,
                phoneNumber = it.phoneNumber,
                address = AddressEntity(
                    zipcode = it.address?.zipcode,
                    streetAddress = it.address?.streetAddress,
                    detailAddress = it.address?.detailAddress
                ),
                profileUrl = it.profileUrl
            )
        }

    override fun toDomain(entity: AccountEntity?): Account? =
        entity?.let {
            Account(
                accountIdx = it.accountIdx,
                id = it.id,
                name = it.name,
                phoneNumber = it.phoneNumber,
                address = Account.Address(
                    zipcode = it.address?.zipcode,
                    streetAddress = it.address?.streetAddress,
                    detailAddress = it.address?.detailAddress
                ),
                profileUrl = it.profileUrl
            )
        }
}