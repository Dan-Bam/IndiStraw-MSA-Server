package com.project.indistraw.account.adapter.input.data.request

import java.util.UUID
import javax.validation.constraints.NotNull

data class QRCodeUUIDRequest(
    @field:NotNull
    val uuid: UUID
)