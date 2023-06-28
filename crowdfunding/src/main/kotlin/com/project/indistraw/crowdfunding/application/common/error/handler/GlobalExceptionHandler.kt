package com.project.indistraw.crowdfunding.application.common.error.handler

import com.project.indistraw.crowdfunding.application.common.error.exception.IndiStrawCrowdfundingException
import com.project.indistraw.crowdfunding.application.common.error.response.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(IndiStrawCrowdfundingException::class)
    fun handler(e: IndiStrawCrowdfundingException): ResponseEntity<ErrorResponse> =
        ResponseEntity(
            ErrorResponse(e.errorCode.message, e.errorCode.status),
            HttpStatus.valueOf(e.errorCode.status)
        )

}