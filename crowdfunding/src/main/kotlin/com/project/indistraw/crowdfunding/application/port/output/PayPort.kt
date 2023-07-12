package com.project.indistraw.crowdfunding.application.port.output

interface PayPort {

    fun confirm(receiptId: String)
    fun cancel(receiptId: String)

}