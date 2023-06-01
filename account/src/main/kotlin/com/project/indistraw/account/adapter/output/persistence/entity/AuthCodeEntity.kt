package com.project.indistraw.account.adapter.output.persistence.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed
import java.util.*

@RedisHash("auth_code")
data class AuthCodeEntity(
    @Id
    val authCode: Int,

    @Indexed
    val phoneNumber: String
)