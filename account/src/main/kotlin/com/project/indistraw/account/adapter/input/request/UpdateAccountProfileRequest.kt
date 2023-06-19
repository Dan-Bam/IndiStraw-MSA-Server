package com.project.indistraw.account.adapter.input.request

import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class UpdateAccountProfileRequest(
    @field:NotNull
    @field:Size(min = 2, max = 10)
    val name: String,

    val profileUrl: String?
)