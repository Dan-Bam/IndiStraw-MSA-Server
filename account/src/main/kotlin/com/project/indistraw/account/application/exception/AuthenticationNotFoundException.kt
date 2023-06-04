package com.project.indistraw.account.application.exception

import com.project.indistraw.account.application.common.error.ErrorCode
import com.project.indistraw.account.application.common.error.exception.IndiStrawAccountException

class AuthenticationNotFoundException: IndiStrawAccountException(ErrorCode.AUTHENTICATION_NOT_FOUND)