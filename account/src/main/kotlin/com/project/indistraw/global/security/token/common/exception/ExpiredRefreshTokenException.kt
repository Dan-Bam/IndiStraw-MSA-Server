package com.project.indistraw.global.security.token.common.exception

import com.project.indistraw.account.application.common.error.exception.IndiStrawAccountException
import com.project.indistraw.account.application.common.error.ErrorCode

class ExpiredRefreshTokenException: IndiStrawAccountException(ErrorCode.EXPIRED_REFRESH_TOKEN)