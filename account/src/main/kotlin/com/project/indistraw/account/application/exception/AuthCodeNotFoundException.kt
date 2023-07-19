package com.project.indistraw.account.application.exception

import com.project.indistraw.global.error.ErrorCode
import com.project.indistraw.global.error.exception.IndiStrawAccountException

class AuthCodeNotFoundException: IndiStrawAccountException(ErrorCode.AUTH_CODE_NOT_FOUND)