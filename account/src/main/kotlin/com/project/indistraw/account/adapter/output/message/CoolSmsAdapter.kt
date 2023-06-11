package com.project.indistraw.account.adapter.output.message

import com.project.indistraw.account.adapter.output.message.properties.CoolSmsProperties
import com.project.indistraw.account.application.port.output.SendMessagePort
import net.nurigo.java_sdk.api.Message
import net.nurigo.java_sdk.exceptions.CoolsmsException
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component


@Component
class CoolSmsAdapter(
    private val coolSmsProperties: CoolSmsProperties
): SendMessagePort {

    @Async
    override fun sendMessage(phoneNumber: String, authCode: Int) {
        val coolsms = Message(coolSmsProperties.access, coolSmsProperties.secret)

        val params: HashMap<String, String> = HashMap()
        params["to"] = phoneNumber
        params["from"] = coolSmsProperties.phoneNumber
        params["type"] = "SMS"
        params["text"] = "IndiStraw 인증번호는 [ $authCode ] 입니다."
        params["app_version"] = "test app 1.2"

        try {
            coolsms.send(params)
        } catch (e: CoolsmsException) {
            throw IllegalArgumentException("메세지 발송에 실패하였습니다.")
        }
    }

}