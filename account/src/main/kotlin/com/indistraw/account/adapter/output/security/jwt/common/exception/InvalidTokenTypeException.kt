package com.indistraw.account.adapter.output.security.jwt.common.exception

import com.indistraw.account.application.common.error.exception.IndiStrawAccountException
import com.indistraw.account.application.common.error.ErrorCode

class InvalidTokenTypeException: IndiStrawAccountException(ErrorCode.INVALID_TOKEN_TYPE)