package com.project.indistraw.account.adapter.output.persistence

import com.project.indistraw.account.adapter.output.persistence.common.converter.AuthCodeConverter
import com.project.indistraw.account.adapter.output.persistence.repository.AuthCodeRepository
import com.project.indistraw.account.application.port.output.CommandAuthCodePort
import com.project.indistraw.account.domain.AuthCode
import org.springframework.stereotype.Component

@Component
class CommandAuthCodePersistenceAdapter(
    private val authCodeRepository: AuthCodeRepository,
    private val authCodeConverter: AuthCodeConverter
): CommandAuthCodePort {

    override fun saveAuthCode(authCode: AuthCode): Int {
        val authCodeEntity = authCodeConverter toEntity authCode
        return authCodeRepository.save(authCodeEntity).authCode
    }

}