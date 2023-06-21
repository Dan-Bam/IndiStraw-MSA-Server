package com.project.indistraw.crowdfunding.application.common.error.exception

import com.project.indistraw.crowdfunding.application.common.error.ErrorCode

open class IndiStrawCrowdfundingException(val errorCode: ErrorCode): RuntimeException(errorCode.message)