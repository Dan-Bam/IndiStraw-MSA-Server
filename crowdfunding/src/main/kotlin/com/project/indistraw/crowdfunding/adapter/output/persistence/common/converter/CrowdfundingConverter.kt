package com.project.indistraw.crowdfunding.adapter.output.persistence.common.converter

import com.project.indistraw.crowdfunding.adapter.output.persistence.entity.AmountEntity
import com.project.indistraw.crowdfunding.adapter.output.persistence.entity.CrowdfundingEntity
import com.project.indistraw.crowdfunding.adapter.output.persistence.entity.DirectorAccountEntity
import com.project.indistraw.crowdfunding.domain.Crowdfunding
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class CrowdfundingConverter {

    infix fun toEntity(domain: Crowdfunding): CrowdfundingEntity =
        domain.let {
            CrowdfundingEntity(
                idx = it.idx,
                writerIdx = it.writerIdx,
                title = it.title,
                description = it.description,
                amount = AmountEntity(
                    totalAmount = it.amount.totalAmount,
                    targetAmount = it.amount.targetAmount
                ),
                directorAccount = DirectorAccountEntity(
                    bank = it.directorAccount.bank,
                    account = it.directorAccount.account
                ),
                createdAt = LocalDateTime.now(),
                endDate = it.endDate,
                viewCount = it.viewCount,
                statusType = it.statusType,
                thumbnailUrl = it.thumbnailUrl,
                imageList = it.imageList,
                fileList = it.fileList
            )
        }

    infix fun toDomain(entity: CrowdfundingEntity?): Crowdfunding? =
        entity?.let {
            Crowdfunding(
                idx = it.idx,
                writerIdx = it.writerIdx,
                title = it.title,
                description = it.description,
                amount = Crowdfunding.Amount(
                    totalAmount = it.amount.totalAmount,
                    targetAmount = it.amount.targetAmount
                ),
                directorAccount = Crowdfunding.DirectorAccount(
                    bank = it.directorAccount.bank,
                    account = it.directorAccount.account
                ),
                createdAt = LocalDateTime.now(),
                endDate = it.endDate,
                viewCount = it.viewCount,
                statusType = it.statusType,
                thumbnailUrl = it.thumbnailUrl,
                imageList = it.imageList,
                fileList = it.fileList
            )
        }

}