package com.project.indistraw.funding.application.port.output

import com.project.indistraw.funding.domain.Funding

interface QueryFundingPort {

    fun findByIdx(idx: Long): Funding?

}