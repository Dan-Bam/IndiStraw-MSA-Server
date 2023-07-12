package com.project.indistraw.crowdfunding.domain

import com.project.indistraw.crowdfunding.application.common.annotation.Aggregate
import com.project.indistraw.crowdfunding.application.common.annotation.AggregateRoot
import java.util.*

@AggregateRoot
class Funding(
    val idx: Long,
    val crowdfundingIdx: Long,
    val rewordIdx: Long,
    val ordererIdx: UUID,
    val price: Long,
    val extraPrice: Long?,
    val activity: Activity
) {

    @Aggregate
    enum class Activity {

        DEPOSITED, NOT_DEPOSIT, DELIVERING, COMPLETED_DELIVERY, CANCEL, RETURN, REFUND

    }

}