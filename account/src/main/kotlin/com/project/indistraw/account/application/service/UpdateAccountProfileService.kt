package com.project.indistraw.account.application.service

import com.project.indistraw.account.application.common.annotation.ServiceWithTransaction
import com.project.indistraw.account.application.exception.AccountNotFoundException
import com.project.indistraw.account.application.port.input.UpdateAccountProfileUseCase
import com.project.indistraw.account.application.port.input.dto.UpdateAccountProfileDto
import com.project.indistraw.account.application.port.output.AccountSecurityPort
import com.project.indistraw.account.application.port.output.CommandAccountPort
import com.project.indistraw.account.application.port.output.QueryAccountPort
import com.project.indistraw.account.domain.Address

@ServiceWithTransaction
class UpdateAccountProfileService(
    private val queryAccountPort: QueryAccountPort,
    private val commandAccountPort: CommandAccountPort,
    private val accountSecurityPort: AccountSecurityPort
): UpdateAccountProfileUseCase {

    override fun execute(dto: UpdateAccountProfileDto) {
        val accountIdx = accountSecurityPort.getCurrentAccountIdx()
        val account = queryAccountPort.findByIdxOrNull(accountIdx)
            ?: throw AccountNotFoundException()
        commandAccountPort.saveAccount(
            account.copy(
                name = dto.name,
                phoneNumber = dto.phoneNumber,
                address = Address(
                    zipcode = dto.address.zipcode,
                    streetAddress = dto.address.streetAddress,
                    detailAddress = dto.address.streetAddress
                ),
                profileUrl = dto.profileUrl
            )
        )
    }

}