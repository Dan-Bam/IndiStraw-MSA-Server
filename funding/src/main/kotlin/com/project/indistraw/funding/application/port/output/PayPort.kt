package com.project.indistraw.funding.application.port.output

interface PayPort {

    fun confirm(receiptId: String)
    fun cancel(receiptId: String)

}