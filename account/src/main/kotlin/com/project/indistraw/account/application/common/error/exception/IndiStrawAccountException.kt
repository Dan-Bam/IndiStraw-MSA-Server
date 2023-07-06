package com.project.indistraw.account.application.common.error.exception

import com.project.indistraw.account.application.common.error.ErrorCode

open class IndiStrawAccountException(val errorCode: ErrorCode): RuntimeException(errorCode.message)