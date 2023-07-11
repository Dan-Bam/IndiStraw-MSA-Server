package com.project.indistraw.funding.adapter.output.pay.exception

import com.project.indistraw.global.error.exception.IndiStrawFundingException
import com.project.indistraw.global.error.ErrorCode

class PayConfirmFailedException: IndiStrawFundingException(ErrorCode.PAY_CONFIRM_FAILED)