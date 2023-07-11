package com.project.indistraw.funding.adapter.output.pay.exception

import com.project.indistraw.global.error.ErrorCode
import com.project.indistraw.global.error.exception.IndiStrawFundingException

class FailedPayCancelException: IndiStrawFundingException(ErrorCode.FAILED_PAY_CANCEL)