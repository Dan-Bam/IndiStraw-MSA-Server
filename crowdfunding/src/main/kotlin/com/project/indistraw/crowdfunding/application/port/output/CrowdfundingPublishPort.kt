package com.project.indistraw.crowdfunding.application.port.output

import com.project.indistraw.crowdfunding.domain.Crowdfunding

interface CrowdfundingPublishPort {

    fun create(crowdfunding: Crowdfunding)

}