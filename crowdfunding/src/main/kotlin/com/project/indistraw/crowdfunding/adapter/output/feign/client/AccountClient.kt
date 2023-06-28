package com.project.indistraw.crowdfunding.adapter.output.feign.client

import com.project.indistraw.crowdfunding.adapter.output.feign.client.data.AccountInfoDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(name = "accountClient", url = "\${account.serviceUrl}")
interface AccountClient {

    @GetMapping("/info")
    fun findAccountInfo(@RequestHeader("Authorization") accessToken: String): AccountInfoDto

}