package com.project.indistraw.account.adapter.output.message.exception

import com.project.indistraw.account.application.common.error.ErrorCode
import com.project.indistraw.account.application.common.error.exception.IndiStrawAccountException

class MessageSendFailedException: IndiStrawAccountException(ErrorCode.MESSAGE_SEND_FAILED)