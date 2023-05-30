package com.indistraw.account.application.exception

import com.indistraw.account.application.common.error.ErrorCode
import com.indistraw.account.application.common.error.exception.IndiStrawAccountException

class AccountNotFoundException: IndiStrawAccountException(ErrorCode.ACCOUNT_NOT_FOUND)