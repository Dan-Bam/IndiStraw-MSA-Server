package com.project.indistraw.funding.adapter.output.pay.exception

import com.project.indistraw.global.error.exception.IndiStrawFundingException
import com.project.indistraw.global.error.ErrorCode

class FailedPayConfirmException: IndiStrawFundingException(ErrorCode.FAILED_PAY_CONFIRM)