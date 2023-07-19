package com.project.indistraw.crowdfunding.application.port.output

import com.project.indistraw.crowdfunding.domain.PayInfo

interface CommandPayInfoPort {

    fun savePayInfo(payInfo: PayInfo)
    fun deletePayInfo(payInfo: PayInfo)

}