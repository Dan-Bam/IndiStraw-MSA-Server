package com.project.indistraw.crowdfunding.adapter.output.persistence

import com.project.indistraw.crowdfunding.adapter.output.persistence.common.converter.CrowdfundingConverter
import com.project.indistraw.crowdfunding.adapter.output.persistence.repository.CrowdfundingRepository
import com.project.indistraw.crowdfunding.adapter.output.persistence.repository.CustomCrowdfundingRepository
import com.project.indistraw.crowdfunding.application.port.output.QueryCrowdfundingPort
import com.project.indistraw.crowdfunding.domain.Crowdfunding
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.*

@Component
class QueryCrowdfundingPersistenceAdapter(
    private val crowdfundingConverter: CrowdfundingConverter,
    private val crowdfundingRepository: CrowdfundingRepository,
    private val customCrowdfundingRepository: CustomCrowdfundingRepository
): QueryCrowdfundingPort {

    override fun findByIdxOrNull(idx: Long): Crowdfunding? {
        val crowdfundingEntity = crowdfundingRepository.findByIdOrNull(idx)
        return crowdfundingConverter toDomain crowdfundingEntity
    }

    override fun findAll(pageRequest: PageRequest): Page<Crowdfunding> {
        val crowdfundingList = crowdfundingRepository.findAllByStatusTypeNot(Crowdfunding.StatusType.UNDER_REVIEW, pageRequest)
        return crowdfundingList.map { crowdfundingConverter toDomain it }
    }

    override fun findTop5ByOrderByViewCount(): List<Crowdfunding> {
        val crowdfundingList = crowdfundingRepository.findTop5ByStatusTypeNotOrderByViewCountDesc(Crowdfunding.StatusType.UNDER_REVIEW)
        return crowdfundingList.map { (crowdfundingConverter toDomain it)!! }
    }

    override fun findByWriterIdx(writerIdx: UUID): List<Crowdfunding> {
        val crowdfundingList = crowdfundingRepository.findByWriterIdx(writerIdx)
        return crowdfundingList.map { (crowdfundingConverter toDomain it)!! }
    }

    override fun findByOrdererIdx(ordererIdx: UUID): List<Crowdfunding> {
        val crowdfundingList = customCrowdfundingRepository.findByOrdererIdx(ordererIdx)
        return crowdfundingList.map { (crowdfundingConverter toDomain it)!! }
    }

    override fun findByTitleOrDescriptionContaining(pageRequest: PageRequest, keyword: String?): Page<Crowdfunding> {
        val crowdfundingEntityList = customCrowdfundingRepository.findByTitleOrDescriptionContaining(pageRequest, keyword)
        val crowdfundingList = crowdfundingEntityList.map { (crowdfundingConverter toDomain it)!! }
        return PageImpl(crowdfundingList.toList(), crowdfundingEntityList.pageable, crowdfundingEntityList.size.toLong())
    }

}