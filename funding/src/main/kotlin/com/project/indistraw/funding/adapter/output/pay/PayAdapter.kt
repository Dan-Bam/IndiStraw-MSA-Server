package com.project.indistraw.funding.adapter.output.pay

import com.project.indistraw.funding.adapter.output.pay.exception.PayConfirmFailedException
import com.project.indistraw.funding.adapter.output.pay.property.BootPayProperties
import com.project.indistraw.funding.application.port.output.PayPort
import kr.co.bootpay.Bootpay
import mu.KotlinLogging
import org.springframework.stereotype.Component

private val log = KotlinLogging.logger {  }

@Component
class PayAdapter(
    private val bootPayProperties: BootPayProperties
): PayPort {

    override fun confirm(receiptId: String) {
        val bootpay = Bootpay(bootPayProperties.restApplicationId, bootPayProperties.privateKey)
        // bootpay 연동 전 accessToken 발급
        bootpay.accessToken
        val confirm = bootpay.confirm(receiptId)

        if (confirm["error_code"] != null) {
            log.error("receiptId confirm failed $receiptId")
            log.error("boot pay confirm failed message is ${confirm["message"]}")
            throw PayConfirmFailedException()
        }

        log.info("receiptId confirm success $receiptId")
    }

    override fun cancel(receiptId: String) {
        
    }

}