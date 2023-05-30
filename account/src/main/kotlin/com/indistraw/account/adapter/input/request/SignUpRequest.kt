package com.indistraw.account.adapter.input.request

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

data class SignUpRequest(
    @field:NotNull
    @field:Length(min = 5, max = 20)
    val id: String,

    @field:NotNull
    @field:Length(min = 8, max = 20)
    @field:Pattern(regexp = "[0-9a-zA-Z]{0,20}")
    val password: String,

    @field:NotNull
    val name: String,

    @field:NotNull
    val phoneNumber: String,

    @field:NotNull
    val profileUrl: String?
)
