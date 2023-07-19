package com.project.indistraw.crowdfunding.adapter.output.pay.exception

import com.project.indistraw.crowdfunding.application.common.error.ErrorCode
import com.project.indistraw.crowdfunding.application.common.error.exception.IndiStrawCrowdfundingException

class FailedPayCancelException: IndiStrawCrowdfundingException(ErrorCode.FAILED_PAY_CANCEL)