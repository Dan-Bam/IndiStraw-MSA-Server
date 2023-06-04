package com.project.indistraw.account.adapter.input.request

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

data class UpdatePasswordRequest(
    @field:NotNull
    @field:Size(min = 10, max = 11)
    val phoneNumber: String,

    @field:NotNull
    @field:Length(min = 8, max = 20)
    @field:Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&*?~])[0-9a-zA-Z!@#$%^&*?~]+$")
    val newPassword: String
)