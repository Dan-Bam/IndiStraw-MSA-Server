package com.project.indistraw.account.application.exception

import com.project.indistraw.account.application.common.error.ErrorCode
import com.project.indistraw.account.application.common.error.exception.IndiStrawAccountException

class AuthCodeNotFoundException: IndiStrawAccountException(ErrorCode.AUTH_CODE_NOT_FOUND)