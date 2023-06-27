package com.project.indistraw.crowdfunding.application.port.output

import com.project.indistraw.crowdfunding.adapter.output.feign.client.data.AccountInfoDto
import java.util.UUID

interface QueryAccountPort {

    fun getAccountInfo(accountIdx: UUID): AccountInfoDto

}