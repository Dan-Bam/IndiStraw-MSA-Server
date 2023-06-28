package com.project.indistraw.crowdfunding.application.common.util

import com.project.indistraw.crowdfunding.domain.Amount
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.math.RoundingMode

@Component
class CalculateAmountUtil {

    fun calculateAmountPercentage(amount: Amount): BigDecimal {
        return if (amount.totalAmount.compareTo(BigDecimal.ZERO) == 0) {
            BigDecimal.ZERO
        } else {
            amount.targetAmount.divide(amount.totalAmount, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100))
        }
    }

}