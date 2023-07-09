package com.project.indistraw.account.adapter.output.sse.exception

import com.project.indistraw.global.error.ErrorCode
import com.project.indistraw.global.error.exception.IndiStrawAccountException

class InvalidSseConnectionException: IndiStrawAccountException(ErrorCode.INVALID_SSE_CONNECTION)