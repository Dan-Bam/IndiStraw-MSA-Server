package com.project.indistraw.account.application.exception

import com.project.indistraw.account.application.common.error.exception.IndiStrawAccountException
import com.project.indistraw.account.application.common.error.ErrorCode

class DuplicatedAccountIdException: IndiStrawAccountException(ErrorCode.DUPLICATE_ACCOUNT_ID)