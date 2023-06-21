package com.project.indistraw.crowdfunding.application.service

import com.project.indistraw.crowdfunding.application.common.annotation.ServiceWithTransaction
import com.project.indistraw.crowdfunding.application.port.input.CreateCrowdfundingUseCase
import com.project.indistraw.crowdfunding.application.port.input.dto.CreateCrowdfundingDto
import com.project.indistraw.crowdfunding.application.port.output.AccountSecurityPort
import com.project.indistraw.crowdfunding.application.port.output.CommandCrowdfundingPort
import com.project.indistraw.crowdfunding.domain.*
import java.math.BigDecimal
import java.time.LocalDateTime

@ServiceWithTransaction
class CreateCrowdfundingService(
    private val accountSecurityPort: AccountSecurityPort,
    private val commandCrowdfundingPort: CommandCrowdfundingPort
): CreateCrowdfundingUseCase {

    override fun execute(dto: CreateCrowdfundingDto): Long {
        val crowdfunding = Crowdfunding(
            idx = 0L,
            title = dto.title,
            accountIdx = accountSecurityPort.getCurrentAccountIdx(),
            description = dto.description,
            amount = Amount(
                targetAmount = BigDecimal.valueOf(dto.targetAmount),
                totalAmount = BigDecimal.ZERO
            ),
            directorAccount = DirectorAccount(
                bank = dto.directorAccount.bank,
                account = dto.directorAccount.account
            ),
            thumbnailUrl = dto.thumbnailUrl,
            date = Date(
                createdDate = LocalDateTime.now(),
                endDate = dto.date.endDate
            ),
            activity = Activity.UNDER_REVIEW,
            imageList = dto.imageList,
            detailList = dto.detailList
        )

        return commandCrowdfundingPort.saveCrowdfunding(crowdfunding)
    }

}