package com.project.indistraw.account.adapter.input

import com.project.indistraw.account.adapter.input.converter.AuthDataConverter
import com.project.indistraw.account.adapter.input.request.SignInRequest
import com.project.indistraw.account.adapter.input.request.SignUpRequest
import com.project.indistraw.account.adapter.input.response.TokenResponse
import com.project.indistraw.account.application.port.input.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("api/v1/auth")
class AuthWebAdapter(
    private val authDataConverter: AuthDataConverter,
    private val signUpUseCase: SignUpUseCase,
    private val signInUseCase: SignInUseCase,
    private val reissueTokenUseCase: ReissueTokenUseCase,
    private val checkAccountIdUseCase: CheckAccountIdUseCase,
    private val checkPhoneNumberUseCase: CheckPhoneNumberUseCase,
    private val sendAuthCodeUseCase: SendAuthCodeUseCase
) {

    @PostMapping("/signup")
    fun signUp(@RequestBody @Valid request: SignUpRequest): ResponseEntity<Void> =
        signUpUseCase.execute(authDataConverter toDto request)
            .run { ResponseEntity.status(HttpStatus.CREATED).build() }

    @PostMapping("/signin")
    fun signIn(@RequestBody @Valid request: SignInRequest): ResponseEntity<TokenResponse> =
        signInUseCase.execute(authDataConverter toDto request)
            .let { authDataConverter toResponse it }
            .let { ResponseEntity.ok(it) }

    @PatchMapping("/reissue")
    fun reissueToken(@RequestHeader refreshToken: String): ResponseEntity<TokenResponse> =
        reissueTokenUseCase.execute(refreshToken)
            .let { authDataConverter toResponse it }
            .let { ResponseEntity.ok(it) }

    @RequestMapping(value = ["/check/id/{id}"], method = [RequestMethod.HEAD])
    fun checkAccountId(@PathVariable id: String): ResponseEntity<Void> =
        checkAccountIdUseCase.execute(id)
            .run { ResponseEntity.status(HttpStatus.NO_CONTENT).build() }

    @RequestMapping(value = ["/check/phone-number/{phoneNumber}"], method = [RequestMethod.HEAD])
    fun checkPhoneNumber(@PathVariable phoneNumber: String): ResponseEntity<Void> =
        checkPhoneNumberUseCase.execute(phoneNumber)
            .run { ResponseEntity.status(HttpStatus.NO_CONTENT).build() }

    @PostMapping("/send/phone-number/{phoneNumber}")
    fun sendAuthCode(@PathVariable phoneNumber: String): ResponseEntity<Void> =
        sendAuthCodeUseCase.execute(phoneNumber)
            .run { ResponseEntity.status(HttpStatus.NO_CONTENT).build() }

}