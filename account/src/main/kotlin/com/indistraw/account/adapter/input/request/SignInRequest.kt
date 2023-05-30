package com.indistraw.account.adapter.input.request

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

data class SignInRequest(
    @field:NotNull
    @field:Length(min = 5, max = 20)
    val id: String,

    @field:NotNull
    @field:Length(min = 8, max = 20)
    @field:Pattern(regexp = "[0-9a-zA-Z]{0,20}")
    val password: String,
)
