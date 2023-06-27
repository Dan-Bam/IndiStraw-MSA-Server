package com.project.indistraw.crowdfunding.adapter.output.feign.error.exception

import com.project.indistraw.crowdfunding.application.common.error.ErrorCode
import com.project.indistraw.crowdfunding.application.common.error.exception.IndiStrawCrowdfundingException

class FeignUnAuthorizedException: IndiStrawCrowdfundingException(ErrorCode.FEIGN_UNAUTHORIZED)