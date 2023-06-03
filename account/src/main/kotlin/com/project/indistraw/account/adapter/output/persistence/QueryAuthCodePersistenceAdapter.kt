package com.project.indistraw.account.adapter.output.persistence

import com.project.indistraw.account.adapter.output.persistence.common.converter.AuthCodeConverter
import com.project.indistraw.account.adapter.output.persistence.repository.AuthCodeRepository
import com.project.indistraw.account.application.port.output.QueryAuthCodePort
import com.project.indistraw.account.domain.AuthCode
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class QueryAuthCodePersistenceAdapter(
    private val authCodeRepository: AuthCodeRepository,
    private val authCodeConverter: AuthCodeConverter
): QueryAuthCodePort {

    override fun findByPhoneNumberOrNull(phoneNumber: String): AuthCode? {
        val authCodeEntity = authCodeRepository.findByIdOrNull(phoneNumber)
        return authCodeConverter toDomain authCodeEntity
    }

}