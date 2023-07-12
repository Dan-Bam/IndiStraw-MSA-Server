package com.project.indistraw.crowdfunding.adapter.output.persistence.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import java.util.*

@RedisHash("pay_info")
data class PayInfoEntity(
    @Id
    val id: String,

    val accountIdx: UUID
)