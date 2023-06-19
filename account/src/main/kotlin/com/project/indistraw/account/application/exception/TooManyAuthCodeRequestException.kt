package com.project.indistraw.account.application.exception

import com.project.indistraw.account.application.common.error.ErrorCode
import com.project.indistraw.account.application.common.error.exception.IndiStrawAccountException

class TooManyAuthCodeRequestException: IndiStrawAccountException(ErrorCode.TOO_MANY_AUTH_CODE_REQUEST)