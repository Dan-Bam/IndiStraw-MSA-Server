package com.project.indistraw.crowdfunding.adapter.output.persistence.entity

import com.project.indistraw.crowdfunding.adapter.output.persistence.common.entity.BaseTimeEntity
import org.springframework.data.redis.core.RedisHash
import java.util.*
import javax.persistence.Id

@RedisHash
data class PayInfoEntity(
    @Id
    val id: String,

    val accountIdx: UUID
): BaseTimeEntity()