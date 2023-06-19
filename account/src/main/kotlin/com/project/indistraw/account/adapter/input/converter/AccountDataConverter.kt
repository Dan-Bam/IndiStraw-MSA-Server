package com.project.indistraw.account.adapter.input.converter

import com.project.indistraw.account.adapter.input.request.UpdateAddressRequest
import com.project.indistraw.account.adapter.input.request.UpdateAccountProfileRequest
import com.project.indistraw.account.adapter.input.request.UpdatePasswordRequest
import com.project.indistraw.account.adapter.input.response.AccountProfileDetailResponse
import com.project.indistraw.account.application.port.input.dto.AccountProfileDetailDto
import com.project.indistraw.account.application.port.input.dto.UpdateAddressDto
import com.project.indistraw.account.application.port.input.dto.UpdateAccountProfileDto
import com.project.indistraw.account.application.port.input.dto.UpdatePasswordDto
import org.springframework.stereotype.Component

@Component
class AccountDataConverter {

    infix fun toDto(request: UpdatePasswordRequest): UpdatePasswordDto =
        UpdatePasswordDto(
            phoneNumber = request.phoneNumber,
            newPassword = request.newPassword
        )

    infix fun toDto(request: UpdateAccountProfileRequest): UpdateAccountProfileDto =
        UpdateAccountProfileDto(
            name = request.name,
            profileUrl = request.profileUrl
        )

    infix fun toDto(request: UpdateAddressRequest): UpdateAddressDto =
        UpdateAddressDto(
            zipcode = request.zipcode,
            streetAddress = request.streetAddress,
            detailAddress = request.detailAddress
        )

    infix fun toResponse(dto: AccountProfileDetailDto): AccountProfileDetailResponse =
        AccountProfileDetailResponse(
            id = dto.id,
            name = dto.name,
            phoneNumber = dto.phoneNumber,
            address = dto.address,
            profileUrl = dto.profileUrl
        )

}