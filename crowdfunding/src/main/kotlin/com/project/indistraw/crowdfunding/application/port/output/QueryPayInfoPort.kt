package com.project.indistraw.crowdfunding.application.port.output

interface QueryPayInfoPort {

    fun existByReceiptId(receiptId: String): Boolean

}