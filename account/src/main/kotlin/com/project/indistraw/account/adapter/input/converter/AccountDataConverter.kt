package com.project.indistraw.account.adapter.input.converter

import com.project.indistraw.account.adapter.input.data.request.UpdateAddressRequest
import com.project.indistraw.account.adapter.input.data.request.UpdateAccountInfoRequest
import com.project.indistraw.account.adapter.input.data.request.UpdatePasswordRequest
import com.project.indistraw.account.adapter.input.data.response.AccountInfoResponse
import com.project.indistraw.account.application.port.input.dto.*
import org.springframework.stereotype.Component

@Component
class AccountDataConverter {

    infix fun toDto(request: UpdatePasswordRequest): UpdatePasswordDto =
        UpdatePasswordDto(
            phoneNumber = request.phoneNumber,
            newPassword = request.newPassword
        )

    infix fun toDto(request: UpdateAccountInfoRequest): UpdateAccountProfileDto =
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

    infix fun toResponse(dto: AccountInfoDto): AccountInfoResponse =
        AccountInfoResponse(
            accountIdx = dto.accountIdx,
            id = dto.id,
            name = dto.name,
            phoneNumber = dto.phoneNumber,
            zipcode = dto.zipcode,
            address = dto.address,
            profileUrl = dto.profileUrl
        )

}