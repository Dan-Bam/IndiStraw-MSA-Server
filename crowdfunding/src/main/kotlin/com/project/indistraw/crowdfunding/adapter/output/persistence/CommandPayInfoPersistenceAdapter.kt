package com.project.indistraw.crowdfunding.adapter.output.persistence

import com.project.indistraw.crowdfunding.adapter.output.persistence.common.converter.PayInfoConverter
import com.project.indistraw.crowdfunding.adapter.output.persistence.repository.PayInfoRepository
import com.project.indistraw.crowdfunding.application.port.output.CommandPayInfoPort
import com.project.indistraw.crowdfunding.domain.PayInfo
import org.springframework.stereotype.Component

@Component
class CommandPayInfoPersistenceAdapter(
    private val payInfoConverter: PayInfoConverter,
    private val payInfoRepository: PayInfoRepository
): CommandPayInfoPort {

    override fun savePayInfo(payInfo: PayInfo) {
        val payInfoEntity = payInfoConverter toEntity  payInfo
        payInfoRepository.save(payInfoEntity)
    }

    override fun deletePayInfo(payInfo: PayInfo) {
        val payInfoEntity = payInfoConverter toEntity  payInfo
        payInfoRepository.delete(payInfoEntity)
    }

}