package com.project.indistraw.account.application.service

import com.project.indistraw.account.application.common.annotation.ServiceWithReadOnlyTransaction
import com.project.indistraw.account.application.exception.AccountNotFoundException
import com.project.indistraw.account.application.port.input.FindAccountInfoUseCase
import com.project.indistraw.account.application.port.input.dto.AccountInfoDto
import com.project.indistraw.account.application.port.output.AccountSecurityPort
import com.project.indistraw.account.application.port.output.QueryAccountPort

@ServiceWithReadOnlyTransaction
class FindAccountInfoService(
    private val queryAccountPort: QueryAccountPort,
    private val accountSecurityPort: AccountSecurityPort
): FindAccountInfoUseCase {

    override fun execute(): AccountInfoDto {
        val accountIdx = accountSecurityPort.getCurrentAccountIdx()
        val account = queryAccountPort.findByIdxOrNull(accountIdx)
            ?: throw AccountNotFoundException()

        return AccountInfoDto(
            accountIdx = account.accountIdx,
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