package com.project.indistraw.crowdfunding.adapter.output.feign.adapter

import com.project.indistraw.crowdfunding.adapter.output.feign.client.AccountClient
import com.project.indistraw.crowdfunding.adapter.output.feign.client.data.AccountInfoDto
import com.project.indistraw.crowdfunding.application.port.output.QueryAccountPort
import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

private val log = KotlinLogging.logger {  }

@Component
class QueryAccountAdapter(
    private val accountClient: AccountClient
): QueryAccountPort {

    override fun getAccountInfo(): AccountInfoDto {
        val accessToken = getAccessToken()
        log.info("accessToken is $accessToken")
        return accountClient.findAccountInfo(accessToken)
    }

    private fun getAccessToken(): String {
        val requestContextHolder = RequestContextHolder.getRequestAttributes() as ServletRequestAttributes
        val httpServletRequest = requestContextHolder.request
        return httpServletRequest.getHeader("Authorization")
    }

}