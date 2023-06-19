package com.project.indistraw.account.application.service

import com.project.indistraw.account.application.common.annotation.ServiceWithReadOnlyTransaction
import com.project.indistraw.account.application.exception.AccountNotFoundException
import com.project.indistraw.account.application.port.input.AccountProfileDetailUseCase
import com.project.indistraw.account.application.port.input.dto.AccountProfileDetailDto
import com.project.indistraw.account.application.port.output.AccountSecurityPort
import com.project.indistraw.account.application.port.output.QueryAccountPort

@ServiceWithReadOnlyTransaction
class AccountProfileDetailService(
    private val queryAccountPort: QueryAccountPort,
    private val accountSecurityPort: AccountSecurityPort
): AccountProfileDetailUseCase {

    override fun execute(): AccountProfileDetailDto {
        val accountIdx = accountSecurityPort.getCurrentAccountIdx()
        val account = queryAccountPort.findByIdxOrNull(accountIdx)
            ?: throw AccountNotFoundException()

        return AccountProfileDetailDto(
            id = account.id,
            name = account.name,
            phoneNumber = account.phoneNumber,
            address = account.address?.let {
                if (it.zipcode.isNullOrBlank()) null
                else it.streetAddress + " " + it.detailAddress
            },
            profileUrl = account.profileUrl
        )
    }

}