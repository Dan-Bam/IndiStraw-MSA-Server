package com.project.indistraw.account.application.exception

import com.project.indistraw.global.error.exception.IndiStrawAccountException
import com.project.indistraw.global.error.ErrorCode

class DuplicatedAccountIdException: IndiStrawAccountException(ErrorCode.DUPLICATE_ACCOUNT_ID)