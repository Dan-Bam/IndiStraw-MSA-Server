package com.project.indistraw.crowdfunding.application.port.output

import com.project.indistraw.crowdfunding.domain.Reword

interface QueryRewordPort {

    fun findByCrowdfundingIdx(crowdfundingIdx: Long): List<Reword>

}