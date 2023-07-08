package com.project.indistraw.account.adapter.output.sse.exception

import com.project.indistraw.account.application.common.error.ErrorCode
import com.project.indistraw.account.application.common.error.exception.IndiStrawAccountException

class InvalidSseConnectionException: IndiStrawAccountException(ErrorCode.INVALID_SSE_CONNECTION)