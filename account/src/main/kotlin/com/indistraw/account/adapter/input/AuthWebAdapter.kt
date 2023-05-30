package com.indistraw.account.adapter.input

import com.indistraw.account.adapter.input.converter.AuthDataConverter
import com.indistraw.account.adapter.input.request.SignInRequest
import com.indistraw.account.adapter.input.request.SignUpRequest
import com.indistraw.account.adapter.input.response.TokenResponse
import com.indistraw.account.application.port.input.SignInUseCase
import com.indistraw.account.application.port.input.SignUpUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("api/v1/auth")
class AuthWebAdapter(
    private val authDataConverter: AuthDataConverter,
    private val signUpUseCase: SignUpUseCase,
    private val signInUseCase: SignInUseCase
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

}