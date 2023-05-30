package com.indistraw.account.application.service

import com.indistraw.account.application.common.annotation.ServiceWithTransaction
import com.indistraw.account.application.exception.AccountNotFoundException
import com.indistraw.account.application.exception.PasswordNotMatchException
import com.indistraw.account.application.port.input.SignInUseCase
import com.indistraw.account.application.port.input.dto.SignInDto
import com.indistraw.account.application.port.output.JwtGeneratePort
import com.indistraw.account.application.port.output.PasswordEncodePort
import com.indistraw.account.application.port.output.QueryAccountPort
import com.indistraw.account.application.port.output.dto.TokenDto

@ServiceWithTransaction
class SignInService(
    private val queryAccountPort: QueryAccountPort,
    private val passwordEncodePort: PasswordEncodePort,
    private val jwtGeneratePort: JwtGeneratePort
): SignInUseCase {

    override fun execute(dto: SignInDto): TokenDto {
        val account = queryAccountPort.findByIdOrNull(dto.id) ?: throw AccountNotFoundException()
        if (!passwordEncodePort.isPasswordMatch(dto.password, account.encodedPassword)) {
            throw PasswordNotMatchException()
        }
        return jwtGeneratePort.generateToken(account.accountIdx, account.authority)
    }

}