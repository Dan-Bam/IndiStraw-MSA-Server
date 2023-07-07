package com.project.indistraw.service

import com.project.indistraw.account.application.exception.AccountNotFoundException
import com.project.indistraw.account.application.port.input.dto.UpdateAddressDto
import com.project.indistraw.account.application.port.output.AccountSecurityPort
import com.project.indistraw.account.application.port.output.CommandAccountPort
import com.project.indistraw.account.application.port.output.QueryAccountPort
import com.project.indistraw.account.application.service.UpdateAddressService
import com.project.indistraw.account.domain.Account
import com.project.indistraw.account.domain.Address
import com.project.indistraw.common.AnyValueObjectGenerator
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.util.UUID

class UpdateAddressServiceTest : BehaviorSpec({
    val accountSecurityPort = mockk<AccountSecurityPort>()
    val commandAccountPort = mockk<CommandAccountPort>()
    val queryAccountPort = mockk<QueryAccountPort>()
    val updateAddressService = UpdateAddressService(accountSecurityPort, commandAccountPort, queryAccountPort)

    Given("updateAddressDto가 주어질때") {
        val updateAddressDto = AnyValueObjectGenerator.anyValueObject<UpdateAddressDto>("zipcode" to "1234")
        val accountIdx = UUID.randomUUID()
        val account = AnyValueObjectGenerator.anyValueObject<Account>("accountIdx" to accountIdx)

        every { accountSecurityPort.getCurrentAccountIdx() } returns accountIdx
        every { queryAccountPort.findByIdxOrNull(accountIdx) } returns account
        every {
            commandAccountPort.saveAccount(
                account.copy(
                    address = Address(
                        zipcode = updateAddressDto.zipcode,
                        streetAddress = updateAddressDto.streetAddress,
                        detailAddress = updateAddressDto.detailAddress
                    )
                )
            )
        } returns account.accountIdx

        When("주소 수정 요청을 하면") {
            updateAddressService.execute(updateAddressDto)

            Then("주소가 수정이 되어야 한다.") {
                verify { commandAccountPort.saveAccount(
                    account.copy(
                        address = Address(
                            zipcode = updateAddressDto.zipcode,
                            streetAddress = updateAddressDto.streetAddress,
                            detailAddress = updateAddressDto.detailAddress
                        )
                    )
                ) }
            }
        }

        When("없는 accountIdx로 요청하면") {
            every { queryAccountPort.findByIdxOrNull(accountIdx) } returns null

            Then("AccountNotFound가 터져야 한다.") {
                shouldThrow<AccountNotFoundException> {
                    updateAddressService.execute(updateAddressDto)
                }
            }
        }
    }

})