package com.indistraw.account.application.port.output

import com.indistraw.account.application.port.output.dto.TokenDto
import com.indistraw.account.domain.Authority
import java.util.*

interface JwtGeneratePort {

    fun generateToken(accountIdx: UUID, authority: Authority): TokenDto

}