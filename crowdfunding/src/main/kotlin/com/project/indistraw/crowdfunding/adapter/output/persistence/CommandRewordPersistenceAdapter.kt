package com.project.indistraw.crowdfunding.adapter.output.persistence

import com.project.indistraw.crowdfunding.adapter.output.persistence.common.converter.RewordConverter
import com.project.indistraw.crowdfunding.adapter.output.persistence.repository.RewordRepository
import com.project.indistraw.crowdfunding.application.port.output.CommandRewordPort
import com.project.indistraw.crowdfunding.domain.Reword
import org.springframework.stereotype.Component

@Component
class CommandRewordPersistenceAdapter(
    private val rewordConverter: RewordConverter,
    private val rewordRepository: RewordRepository
): CommandRewordPort {

    override fun saveAllReword(rewordList: List<Reword>) {
        val rewordEntityList = rewordConverter toEntityList rewordList
        rewordRepository.saveAll(rewordEntityList)
    }

}