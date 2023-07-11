package com.project.indistraw.global.error.exception

import com.project.indistraw.global.error.ErrorCode

open class IndiStrawFundingException(val errorCode: ErrorCode): RuntimeException(errorCode.message)