package com.project.indistraw.crowdfunding.adapter.output.feign.adapter

import com.project.indistraw.crowdfunding.adapter.output.feign.client.AccountClient
import com.project.indistraw.crowdfunding.adapter.output.feign.client.data.AccountInfoDto
import com.project.indistraw.crowdfunding.application.port.output.QueryAccountPort
import org.springframework.stereotype.Component
import java.util.*

@Component
class QueryAccountAdapter(
    private val accountClient: AccountClient
): QueryAccountPort {

    override fun getAccountInfo(accountIdx: UUID): AccountInfoDto {
        return accountClient.findAccountInfo(accountIdx)
    }

}