package com.project.indistraw.account.adapter.output.message.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("coolsms")
data class CoolSmsProperties(
    val access: String,
    val secret: String,
    val phoneNumber: String
)