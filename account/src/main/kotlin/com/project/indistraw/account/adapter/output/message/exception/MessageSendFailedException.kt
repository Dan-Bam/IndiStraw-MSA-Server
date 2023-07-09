package com.project.indistraw.account.adapter.output.message.exception

import com.project.indistraw.global.error.ErrorCode
import com.project.indistraw.global.error.exception.IndiStrawAccountException

class MessageSendFailedException: IndiStrawAccountException(ErrorCode.MESSAGE_SEND_FAILED)