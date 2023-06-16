package com.project.indistraw.account.application.port.output

interface SendMessagePort {

    fun sendMessage(phoneNumber: String, authCode: Int)

}