package com.project.indistraw.account.adapter.input.converter

import com.project.indistraw.account.adapter.input.request.SignInRequest
import com.project.indistraw.account.adapter.input.request.SignUpRequest
import com.project.indistraw.account.adapter.input.response.TokenResponse
import com.project.indistraw.account.application.port.input.dto.SignInDto
import com.project.indistraw.account.application.port.input.dto.SignUpDto
import com.project.indistraw.account.application.port.output.dto.TokenDto
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class AuthDataConverter {

    infix fun toDto(request: SignUpRequest): SignUpDto =
        SignUpDto(
            id = request.id,
            password = request.password,
            name = request.name,
            phoneNumber = request.phoneNumber,
            profileUrl = request.profileUrl
        )

    infix fun toDto(request: SignInRequest): SignInDto =
        SignInDto(
            id = request.id,
            password = request.password
        )

    infix fun toResponse(dto: TokenDto): TokenResponse =
        TokenResponse(
            accessToken = dto.accessToken,
            refreshToken = dto.refreshToken,
            accessTokenExpiredAt = LocalDateTime.now().plusSeconds(dto.accessTokenExpiredAt),
            refreshTokenExpiredAt = LocalDateTime.now().plusSeconds(dto.refreshTokenExpiredAt)
        )

}