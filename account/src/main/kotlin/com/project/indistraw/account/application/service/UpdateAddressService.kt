package com.project.indistraw.account.application.service

import com.project.indistraw.account.application.common.annotation.ServiceWithTransaction
import com.project.indistraw.account.application.exception.AccountNotFoundException
import com.project.indistraw.account.application.port.input.UpdateAddressUseCase
import com.project.indistraw.account.application.port.input.dto.UpdateAddressDto
import com.project.indistraw.account.application.port.output.AccountSecurityPort
import com.project.indistraw.account.application.port.output.CommandAccountPort
import com.project.indistraw.account.application.port.output.QueryAccountPort
import com.project.indistraw.account.domain.Address

@ServiceWithTransaction
class UpdateAddressService(
    private val accountSecurityPort: AccountSecurityPort,
    private val commandAccountPort: CommandAccountPort,
    private val queryAccountPort: QueryAccountPort
): UpdateAddressUseCase {

    override fun execute(updateAddressDto: UpdateAddressDto) {
        val accountIdx = accountSecurityPort.getCurrentAccountIdx()
        val account = queryAccountPort.findByIdxOrNull(accountIdx)
            ?: throw AccountNotFoundException()

        commandAccountPort.saveAccount(
            account.copy(
                address = Address(
                    zipcode = updateAddressDto.zipcode,
                    streetAddress = updateAddressDto.streetAddress,
                    detailAddress = updateAddressDto.detailAddress
                )
            )
        )
    }
}