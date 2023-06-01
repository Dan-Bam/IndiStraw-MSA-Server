package com.project.indistraw.account.application.exception

import com.project.indistraw.account.application.common.error.ErrorCode
import com.project.indistraw.account.application.common.error.exception.IndiStrawAccountException

class PasswordNotMatchException: IndiStrawAccountException(ErrorCode.PASSWORD_NOT_MATCH)