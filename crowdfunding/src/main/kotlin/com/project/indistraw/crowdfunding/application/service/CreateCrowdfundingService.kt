package com.project.indistraw.crowdfunding.application.service

import com.project.indistraw.crowdfunding.application.common.annotation.ServiceWithTransaction
import com.project.indistraw.crowdfunding.application.port.input.CreateCrowdfundingUseCase
import com.project.indistraw.crowdfunding.application.port.input.dto.CreateCrowdfundingDto
import com.project.indistraw.crowdfunding.application.port.output.AccountSecurityPort
import com.project.indistraw.crowdfunding.application.port.output.CommandCrowdfundingPort
import com.project.indistraw.crowdfunding.application.port.output.CommandRewordPort
import com.project.indistraw.crowdfunding.domain.*
import java.math.BigDecimal
import java.time.LocalDateTime

@ServiceWithTransaction
class CreateCrowdfundingService(
    private val accountSecurityPort: AccountSecurityPort,
    private val commandCrowdfundingPort: CommandCrowdfundingPort,
    private val commandRewordPort: CommandRewordPort
): CreateCrowdfundingUseCase {

    override fun execute(dto: CreateCrowdfundingDto) {
        // dto값을 토대로 crowdfunding 객체를 생성하여 저장합니다.
        val crowdfunding = Crowdfunding(
            idx = 0L,
            accountIdx = accountSecurityPort.getCurrentAccountIdx(),
            title = dto.title,
            description = dto.description,
            amount = Amount(
                targetAmount = BigDecimal.valueOf(dto.targetAmount),
                totalAmount = BigDecimal.ZERO
            ),
            directorAccount = DirectorAccount(
                bank = dto.directorAccount.bank,
                account = dto.directorAccount.account
            ),
            createdAt = LocalDateTime.now(),
            endDate = dto.endDate,
            viewCount = 0,
            activity = Activity.UNDER_REVIEW,
            thumbnailUrl = dto.thumbnailUrl,
            imageList = dto.imageList,
            detailList = dto.detailList
        )
        val crowdfundingIdx = commandCrowdfundingPort.saveCrowdfunding(crowdfunding)

        val rewordList = dto.reword.map {
            Reword(
                idx = 0L,
                title = it.title,
                description = it.description,
                price = it.price,
                imageUrl = it.imageUrl,
                crowdfundingIdx = crowdfundingIdx
            )
        }

        commandRewordPort.saveAllReword(rewordList)
    }

}