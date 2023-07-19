package com.project.indistraw.global.security.token.common.exception

import com.project.indistraw.global.error.exception.IndiStrawAccountException
import com.project.indistraw.global.error.ErrorCode

class InvalidTokenException: IndiStrawAccountException(ErrorCode.INVALID_TOKEN)