package com.project.indistraw.account.adapter.input

import com.project.indistraw.account.adapter.input.converter.AccountDataConverter
import com.project.indistraw.account.adapter.input.request.UpdateAddressRequest
import com.project.indistraw.account.adapter.input.request.UpdateAccountInfoRequest
import com.project.indistraw.account.adapter.input.request.UpdatePasswordRequest
import com.project.indistraw.account.adapter.input.response.AccountInfoResponse
import com.project.indistraw.account.application.port.input.*
import com.project.indistraw.account.application.port.output.UpdatePhoneNumberUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/account")
class AccountWebAdapter(
    private val accountDataConverter: AccountDataConverter,
    private val findAccountIdUseCase: FindAccountIdUseCase,
    private val updatePasswordUseCase: UpdatePasswordUseCase,
    private val updatePhoneNumberUseCase: UpdatePhoneNumberUseCase,
    private val updateAddressUseCase: UpdateAddressUseCase,
    private val updateAccountInfoUseCase: UpdateAccountInfoUseCase,
    private val findAccountInfoUseCase: FindAccountInfoUseCase,
    private val accountWithdrawUseCase: AccountWithdrawUseCase
) {

    @GetMapping("/phone-number/{phoneNumber}")
    fun findAccountId(@PathVariable phoneNumber: String): ResponseEntity<Map<String, String>> =
        findAccountIdUseCase.execute(phoneNumber)
            .let { ResponseEntity.ok(mapOf("id" to it)) }

    @PatchMapping("/password")
    fun updatePassword(@RequestBody request: UpdatePasswordRequest): ResponseEntity<Void> =
        updatePasswordUseCase.execute(accountDataConverter toDto request)
            .run { ResponseEntity.status(HttpStatus.RESET_CONTENT).build() }

    @PatchMapping("/phone-number/{phoneNumber}")
    fun updatePhoneNumber(@PathVariable phoneNumber: String): ResponseEntity<Void> =
        updatePhoneNumberUseCase.execute(phoneNumber)
            .run { ResponseEntity.status(HttpStatus.RESET_CONTENT).build() }

    @PatchMapping("/address")
    fun updateAddress(@RequestBody updateAddressRequest: UpdateAddressRequest): ResponseEntity<Void> =
        updateAddressUseCase.execute(accountDataConverter toDto updateAddressRequest)
            .run { ResponseEntity.status(HttpStatus.RESET_CONTENT).build() }

    @PatchMapping("/info")
    fun updateAccountInfo(@RequestBody request: UpdateAccountInfoRequest): ResponseEntity<Void> =
        updateAccountInfoUseCase.execute(accountDataConverter toDto request)
            .run { ResponseEntity.status(HttpStatus.RESET_CONTENT).build() }

    @GetMapping("/info")
    fun findAccountInfo(): ResponseEntity<AccountInfoResponse> =
        findAccountInfoUseCase.execute()
            .let { accountDataConverter toResponse it }
            .let { ResponseEntity.ok(it) }

    @DeleteMapping
    fun accountWithdraw(): ResponseEntity<Void> =
        accountWithdrawUseCase.execute()
            .run { ResponseEntity.status(HttpStatus.RESET_CONTENT).build() }

}