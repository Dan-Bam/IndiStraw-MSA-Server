package com.project.indistraw.crowdfunding.application.port.output

import java.util.UUID

interface PayPort {

    fun confirm(receiptId: UUID)
    fun cancel(receiptId: UUID)

}