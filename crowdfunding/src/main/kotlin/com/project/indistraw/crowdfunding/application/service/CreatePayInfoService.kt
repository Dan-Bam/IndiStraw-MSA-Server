package com.project.indistraw.crowdfunding.application.service

import com.project.indistraw.crowdfunding.application.common.annotation.ServiceWithTransaction
import com.project.indistraw.crowdfunding.application.port.input.CreatePayInfoUseCase
import com.project.indistraw.crowdfunding.application.port.output.AccountSecurityPort
import com.project.indistraw.crowdfunding.application.port.output.CommandPayInfoPort
import com.project.indistraw.crowdfunding.domain.PayInfo
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@ServiceWithTransaction
class CreatePayInfoService(
    private val commandPayInfoPort: CommandPayInfoPort,
    private val accountSecurityPort: AccountSecurityPort
): CreatePayInfoUseCase {

    override fun execute(): String {
        val currentAccountIdx = accountSecurityPort.getCurrentAccountIdx()
        val payInfo = PayInfo(
            receiptId = generateReceiptId(currentAccountIdx),
            accountIdx = currentAccountIdx
        )
        commandPayInfoPort.savePayInfo(payInfo)
        return payInfo.receiptId
    }

    private fun generateReceiptId(accountIdx: UUID): String {
        val currentTime = LocalDateTime.now()
        val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH-mm-ss")
        val time = currentTime.format(dateFormatter).replace("-", "")
        return "$time$accountIdx"
    }

}