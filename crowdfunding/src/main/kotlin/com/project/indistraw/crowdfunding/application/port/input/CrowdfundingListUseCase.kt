package com.project.indistraw.crowdfunding.application.port.input

import com.project.indistraw.crowdfunding.application.port.input.dto.CrowdfundingPagingDto
import org.springframework.data.domain.Pageable

interface CrowdfundingListUseCase {

    fun execute(pageable: Pageable): CrowdfundingPagingDto

}