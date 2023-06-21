package com.project.indistraw.crowdfunding.adapter.output.persistence.common.converter

import com.project.indistraw.crowdfunding.adapter.output.persistence.entity.AmountEntity
import com.project.indistraw.crowdfunding.adapter.output.persistence.entity.CrowdfundingEntity
import com.project.indistraw.crowdfunding.adapter.output.persistence.entity.DateEntity
import com.project.indistraw.crowdfunding.adapter.output.persistence.entity.DirectorAccountEntity
import com.project.indistraw.crowdfunding.domain.Amount
import com.project.indistraw.crowdfunding.domain.Crowdfunding
import com.project.indistraw.crowdfunding.domain.Date
import com.project.indistraw.crowdfunding.domain.DirectorAccount
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class CrowdfundingConverter {

    infix fun toEntity(domain: Crowdfunding): CrowdfundingEntity =
        domain.let {
            CrowdfundingEntity(
                idx = it.idx,
                accountIdx = it.accountIdx,
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
                thumbnailUrl = it.thumbnailUrl,
                date = DateEntity(
                    createdDate = LocalDateTime.now(),
                    endDate = it.date.endDate
                ),
                activity = it.activity,
                imageList = it.imageList,
                detailList = it.detailList
            )
        }

    infix fun toDomain(entity: CrowdfundingEntity?): Crowdfunding? =
        entity?.let {
            Crowdfunding(
                idx = it.idx,
                accountIdx = it.accountIdx,
                title = it.title,
                description = it.description,
                amount = Amount(
                    totalAmount = it.amount.totalAmount,
                    targetAmount = it.amount.targetAmount
                ),
                directorAccount = DirectorAccount(
                    bank = it.directorAccount.bank,
                    account = it.directorAccount.account
                ),
                thumbnailUrl = it.thumbnailUrl,
                date = Date(
                    createdDate = LocalDateTime.now(),
                    endDate = it.date.endDate
                ),
                activity = it.activity,
                imageList = it.imageList,
                detailList = it.detailList
            )
        }

}