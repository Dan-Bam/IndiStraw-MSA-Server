package com.project.indistraw.crowdfunding.application.port.output

import com.project.indistraw.crowdfunding.adapter.output.feign.client.data.AccountInfoDto

interface QueryAccountPort {

    fun getAccountInfo(): AccountInfoDto

}