package com.project.indistraw.crowdfunding.application.port.output

import com.project.indistraw.crowdfunding.domain.Account
import com.project.indistraw.crowdfunding.domain.Funding
import java.util.UUID

interface QueryFundingPort {

    fun findByIdx(idx: Long): Funding?
    fun existByOrdererIdx(ordererIdx: UUID): Boolean
    fun countByCrowdfundingIdx(crowdfundingIdx: Long): Long
    fun findOrdererByCrowdfundingIdx(crowdfundingIdx: Long): List<Account>

}