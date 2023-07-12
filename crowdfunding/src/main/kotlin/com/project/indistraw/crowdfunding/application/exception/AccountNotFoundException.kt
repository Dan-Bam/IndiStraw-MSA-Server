package com.project.indistraw.crowdfunding.application.exception

import com.project.indistraw.crowdfunding.application.common.error.ErrorCode
import com.project.indistraw.crowdfunding.application.common.error.exception.IndiStrawCrowdfundingException

class AccountNotFoundException: IndiStrawCrowdfundingException(ErrorCode.ACCOUNT_NOT_FOUND)