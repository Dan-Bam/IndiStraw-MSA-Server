package com.project.indistraw.account.application.service

import com.project.indistraw.account.application.common.annotation.ServiceWithReadOnlyTransaction
import com.project.indistraw.account.application.exception.AccountNotFoundException
import com.project.indistraw.account.application.exception.PasswordNotMatchException
import com.project.indistraw.account.application.port.input.SignInUseCase
import com.project.indistraw.account.application.port.input.dto.SignInDto
import com.project.indistraw.account.application.port.output.JwtGeneratePort
import com.project.indistraw.account.application.port.output.PasswordEncodePort
import com.project.indistraw.account.application.port.output.QueryAccountPort
import com.project.indistraw.account.application.port.output.dto.TokenDto

@ServiceWithReadOnlyTransaction
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