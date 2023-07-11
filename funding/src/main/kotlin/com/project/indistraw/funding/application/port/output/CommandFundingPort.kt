package com.project.indistraw.funding.application.port.output

import com.project.indistraw.funding.domain.Funding

interface CommandFundingPort {

    fun saveFunding(funding: Funding)
    fun deleteFunding(funding: Funding)

}