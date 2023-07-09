package com.project.indistraw.global.security.token.common.exception

import com.project.indistraw.global.error.exception.IndiStrawAccountException
import com.project.indistraw.global.error.ErrorCode

class ExpiredRefreshTokenException: IndiStrawAccountException(ErrorCode.EXPIRED_REFRESH_TOKEN)