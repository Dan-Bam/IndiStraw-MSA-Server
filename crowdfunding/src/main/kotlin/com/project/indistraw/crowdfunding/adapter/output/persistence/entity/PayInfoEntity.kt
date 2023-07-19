package com.project.indistraw.crowdfunding.adapter.output.persistence.entity

import com.project.indistraw.crowdfunding.adapter.output.persistence.common.entity.BaseTimeEntity
import java.util.*
import javax.persistence.Entity

@Entity(name = "pay_info")
class PayInfoEntity(
    @javax.persistence.Id
    val id: String,

    val accountIdx: UUID
): BaseTimeEntity()