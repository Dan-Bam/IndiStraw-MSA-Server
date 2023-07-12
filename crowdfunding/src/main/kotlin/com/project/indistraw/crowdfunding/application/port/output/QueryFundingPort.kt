package com.project.indistraw.crowdfunding.application.port.output

import com.project.indistraw.crowdfunding.domain.Funding

interface QueryFundingPort {

    fun findByIdx(idx: Long): Funding?

}