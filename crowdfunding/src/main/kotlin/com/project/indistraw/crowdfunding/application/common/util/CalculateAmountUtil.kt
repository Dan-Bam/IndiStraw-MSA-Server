package com.project.indistraw.crowdfunding.application.common.util

import com.project.indistraw.crowdfunding.domain.Amount
import org.springframework.stereotype.Component

@Component
class CalculateAmountUtil {

    fun calculateAmountPercentage(amount: Amount): Double {
        return if (amount.totalAmount == 0L) {
            0.0
        } else {
            amount.totalAmount.toDouble() / amount.targetAmount.toDouble() * 100
        }
    }

}