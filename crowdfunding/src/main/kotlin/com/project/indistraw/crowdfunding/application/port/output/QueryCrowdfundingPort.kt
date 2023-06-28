package com.project.indistraw.crowdfunding.application.port.output

import com.project.indistraw.crowdfunding.domain.Crowdfunding
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest

interface QueryCrowdfundingPort {

    fun findByIdxOrNull(idx: Long): Crowdfunding?
    fun findAll(pageRequest: PageRequest): Page<Crowdfunding>
    fun findTop7ByOrderByViewCount(): List<Crowdfunding>

}