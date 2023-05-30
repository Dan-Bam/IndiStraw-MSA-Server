package com.indistraw.account.application.common.error.exception

import com.indistraw.account.application.common.error.ErrorCode

open class IndiStrawAccountException(val errorCode: ErrorCode): RuntimeException(errorCode.message)