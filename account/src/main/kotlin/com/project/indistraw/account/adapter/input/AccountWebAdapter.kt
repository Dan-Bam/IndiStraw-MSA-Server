package com.project.indistraw.account.adapter.input

import com.project.indistraw.account.adapter.input.converter.AccountDataConverter
import com.project.indistraw.account.adapter.input.request.UpdateAccountProfileRequest
import com.project.indistraw.account.adapter.input.request.UpdatePasswordRequest
import com.project.indistraw.account.adapter.input.response.AccountProfileDetailResponse
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
    private val updateAccountProfileUseCase: UpdateAccountProfileUseCase,
    private val accountProfileDetailUseCase: AccountProfileDetailUseCase,
    private val accountWithdrawUseCase: AccountWithdrawUseCase
) {

    @GetMapping("/phone-number/{phoneNumber}")
    fun findAccountId(@PathVariable phoneNumber: String): ResponseEntity<Map<String, String>> =
        findAccountIdUseCase.execute(phoneNumber)
            .let { ResponseEntity.ok(mapOf("id" to it)) }

    @PatchMapping("/password")
    fun sendAuthCode(@RequestBody request: UpdatePasswordRequest): ResponseEntity<Void> =
        updatePasswordUseCase.execute(accountDataConverter toDto request)
            .run { ResponseEntity.status(HttpStatus.RESET_CONTENT).build() }

    @PatchMapping("/phone-number/{phoneNumber}")
    fun sendAuthCode(@PathVariable phoneNumber: String): ResponseEntity<Void> =
        updatePhoneNumberUseCase.execute(phoneNumber)
            .run { ResponseEntity.status(HttpStatus.RESET_CONTENT).build() }

    @PatchMapping("/profile")
    fun sendAuthCode(@RequestBody request: UpdateAccountProfileRequest): ResponseEntity<Void> =
        updateAccountProfileUseCase.execute(accountDataConverter toDto request)
            .run { ResponseEntity.status(HttpStatus.RESET_CONTENT).build() }

    @GetMapping("/profile")
    fun findProfileDetail(): ResponseEntity<AccountProfileDetailResponse> =
        accountProfileDetailUseCase.execute()
            .let { accountDataConverter toResponse it }
            .let { ResponseEntity.ok(it) }

    @DeleteMapping
    fun accountWithdraw(): ResponseEntity<Void> =
        accountWithdrawUseCase.execute()
            .run { ResponseEntity.status(HttpStatus.RESET_CONTENT).build() }

}