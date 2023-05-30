package com.indistraw.account.adapter.input.request

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.Max
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

data class SignUpRequest(
    @field:NotNull
    @field:Length(min = 5, max = 20)
    val id: String,

    @field:NotNull
    @field:Length(min = 8, max = 20)
    @field:Pattern(regexp = "[0-9a-zA-Z]{0,20}")
    val password: String,

    @field:NotNull
    @field:Max(10)
    val name: String,

    @field:NotNull
    @field:Size(min = 10, max = 11)
    val phoneNumber: String,

    @field:NotNull
    val profileUrl: String?
)
