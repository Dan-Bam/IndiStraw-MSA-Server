package com.project.indistraw.crowdfunding.adapter.output.feign.client

import com.project.indistraw.crowdfunding.adapter.output.feign.client.data.AccountInfoDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import java.util.UUID

@FeignClient(name = "accountClient", url = "\${service.scheme}://\${service.account.host}")
interface AccountClient {

    @GetMapping("/account/id/{accountIdx}")
    fun findAccountInfo(@PathVariable accountIdx: UUID): AccountInfoDto

}