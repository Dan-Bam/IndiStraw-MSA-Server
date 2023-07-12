package com.project.indistraw.crowdfunding.application.port.output

import com.project.indistraw.crowdfunding.domain.Funding

interface CommandFundingPort {

    fun saveFunding(funding: Funding)
    fun deleteFunding(funding: Funding)

}