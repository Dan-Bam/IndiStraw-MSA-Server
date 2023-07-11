package com.project.indistraw.funding.application.exception

import com.project.indistraw.global.error.ErrorCode
import com.project.indistraw.global.error.exception.IndiStrawFundingException

class FundingNotFoundException: IndiStrawFundingException(ErrorCode.FUNDING_NOT_FOUND)