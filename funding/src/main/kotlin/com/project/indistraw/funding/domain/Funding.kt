package com.project.indistraw.funding.domain

import com.project.indistraw.funding.application.common.annotation.AggregateRoot
import java.util.UUID

@AggregateRoot
class Funding(
    val idx: Long,
    val crowdfundingIdx: Long,
    val rewordIdx: Long,
    val ordererIdx: UUID,
    val price: Long,
    val extraPrice: Long?,
    var activity: Activity
) {

    fun updateActivity(activity: Activity) {
        this.activity = activity
    }

}