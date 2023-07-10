package com.project.indistraw.funding.domain

import com.project.indistraw.funding.application.common.annotation.Aggregate

@Aggregate
enum class Activity {

    DEPOSITED, NOT_DEPOSIT, DELIVERING, COMPLETED_DELIVERY, CANCEL, RETURN, REFUND

}