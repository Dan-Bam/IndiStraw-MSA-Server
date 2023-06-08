package com.project.indistraw.account.application.exception

import com.project.indistraw.account.application.common.error.ErrorCode
import com.project.indistraw.account.application.common.error.exception.IndiStrawAccountException

class TooManyAuthenticationRequestException: IndiStrawAccountException(ErrorCode.TOO_MANY_AUTHENTICATION_REQUEST)