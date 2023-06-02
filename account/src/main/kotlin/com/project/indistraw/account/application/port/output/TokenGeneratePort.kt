package com.project.indistraw.account.application.port.output

import com.project.indistraw.account.application.port.output.dto.TokenDto
import com.project.indistraw.account.domain.Authority
import java.util.*

interface TokenGeneratePort {

    fun generateToken(accountIdx: UUID, authority: Authority): TokenDto

}