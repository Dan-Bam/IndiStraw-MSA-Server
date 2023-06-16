package com.project.indistraw.global.security.token.common.exception

import com.project.indistraw.account.application.common.error.exception.IndiStrawAccountException
import com.project.indistraw.account.application.common.error.ErrorCode

class InvalidTokenTypeException: IndiStrawAccountException(ErrorCode.INVALID_TOKEN_TYPE)