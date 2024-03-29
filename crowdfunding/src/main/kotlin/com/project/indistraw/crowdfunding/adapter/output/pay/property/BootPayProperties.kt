package com.project.indistraw.crowdfunding.adapter.output.pay.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("bootpay")
class BootPayProperties(
    val restApplicationId: String,
    val privateKey: String
)