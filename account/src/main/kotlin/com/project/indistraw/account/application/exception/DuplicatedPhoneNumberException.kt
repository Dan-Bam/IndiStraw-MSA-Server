package com.project.indistraw.account.application.exception

import com.project.indistraw.global.error.ErrorCode
import com.project.indistraw.global.error.exception.IndiStrawAccountException

class DuplicatedPhoneNumberException: IndiStrawAccountException(ErrorCode.DUPLICATE_PHONE_NUMBER)