package com.project.indistraw.crowdfunding.adapter.output.feign.error

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.project.indistraw.crowdfunding.adapter.output.feign.error.exception.FeignBadRequestException
import com.project.indistraw.crowdfunding.adapter.output.feign.error.exception.FeignForbiddenException
import com.project.indistraw.crowdfunding.adapter.output.feign.error.exception.FeignNotFoundException
import com.project.indistraw.crowdfunding.adapter.output.feign.error.exception.FeignUnAuthorizedException
import feign.FeignException
import feign.Response
import feign.codec.ErrorDecoder
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class FeignClientErrorDecoder: ErrorDecoder {

    override fun decode(methodKey: String, response: Response): Exception {
        if (response.status() >= 400) {
            when (response.status()) {
                400 -> throw FeignBadRequestException()
                401 -> throw FeignUnAuthorizedException()
                403 -> throw FeignForbiddenException()
                404 -> throw FeignNotFoundException()
                else -> throw ResponseStatusException(
                    HttpStatus.valueOf(response.status()),
                    readException(response).message
                )
            }
        }
        return FeignException.errorStatus(methodKey, response)
    }

    private fun readException(response: Response): ExceptionMessage {
        return response.body().asInputStream().use {
            val mapper = ObjectMapper()
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            mapper.readValue(it, ExceptionMessage::class.java)
        }
    }

    data class ExceptionMessage(
        val status: Int,
        val message: String?
    )

}