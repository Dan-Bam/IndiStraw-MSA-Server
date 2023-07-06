package com.project.indistraw.account.application.exception

import com.project.indistraw.account.application.common.error.ErrorCode
import com.project.indistraw.account.application.common.error.exception.IndiStrawAccountException

class InvalidQRCodeUUIDException: IndiStrawAccountException(ErrorCode.INVALID_QRCODE_UUID)