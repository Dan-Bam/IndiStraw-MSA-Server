package com.project.indistraw.crowdfunding.adapter.output.feign.error

import com.project.indistraw.crowdfunding.adapter.output.feign.error.exception.FeignBadRequestException
import com.project.indistraw.crowdfunding.adapter.output.feign.error.exception.FeignForbiddenException
import com.project.indistraw.crowdfunding.adapter.output.feign.error.exception.FeignUnAuthorizedException
import feign.FeignException
import feign.Response
import feign.codec.ErrorDecoder

class FeignClientErrorDecoder: ErrorDecoder {

    override fun decode(methodKey: String, response: Response): Exception {
        if (response.status() >= 400) {
            when (response.status()) {
                401 -> throw FeignUnAuthorizedException()
                403 -> throw FeignForbiddenException()
                else -> throw FeignBadRequestException()
            }
        }

        return FeignException.errorStatus(methodKey, response)
    }
}