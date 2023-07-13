package com.project.indistraw.crowdfunding.adapter.output.persistence.common.converter

import com.project.indistraw.crowdfunding.adapter.output.persistence.entity.AccountEntity
import com.project.indistraw.crowdfunding.adapter.output.persistence.entity.AddressEntity
import com.project.indistraw.crowdfunding.domain.Account
import org.springframework.stereotype.Component

@Component
class AccountConverter {

    fun toEntity(domain: Account): AccountEntity =
        domain.let {
            AccountEntity(
                accountIdx = it.accountIdx,
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

}