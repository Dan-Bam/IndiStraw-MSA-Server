package com.project.indistraw.crowdfunding.application.service

import com.project.indistraw.crowdfunding.application.common.annotation.ServiceWithTransaction
import com.project.indistraw.crowdfunding.application.port.input.CreateCrowdfundingUseCase
import com.project.indistraw.crowdfunding.application.port.input.dto.CreateCrowdfundingDto
import com.project.indistraw.crowdfunding.application.port.output.AccountSecurityPort
import com.project.indistraw.crowdfunding.application.port.output.CommandCrowdfundingPort
import com.project.indistraw.crowdfunding.application.port.output.CommandRewordPort
import com.project.indistraw.crowdfunding.application.port.output.CrowdfundingPublishPort
import com.project.indistraw.crowdfunding.domain.*
import java.time.LocalDateTime

@ServiceWithTransaction
class CreateCrowdfundingService(
    private val accountSecurityPort: AccountSecurityPort,
    private val commandCrowdfundingPort: CommandCrowdfundingPort,
    private val commandRewordPort: CommandRewordPort,
    private val crowdfundingPublishPort: CrowdfundingPublishPort
): CreateCrowdfundingUseCase {

    override fun execute(dto: CreateCrowdfundingDto) {
        // dto값을 토대로 crowdfunding 객체를 생성하여 저장합니다.
        val crowdfunding = Crowdfunding(
            idx = 0L,
            writerIdx = accountSecurityPort.getCurrentAccountIdx(),
            title = dto.title,
            description = dto.description,
            amount = Amount(
                targetAmount = dto.targetAmount,
                totalAmount = 0L
            ),
            directorAccount = DirectorAccount(
                bank = dto.directorAccount.bank,
                account = dto.directorAccount.account
            ),
            createdAt = LocalDateTime.now(),
            endDate = dto.endDate,
            viewCount = 0,
            statusType = StatusType.UNDER_REVIEW,
            thumbnailUrl = dto.thumbnailUrl,
            imageList = dto.imageList,
            fileList = dto.fileList
        )
        val crowdfundingIdx = commandCrowdfundingPort.saveCrowdfunding(crowdfunding)

        // dto값을 토대로 reword 객체를 생성하여 저장합니다.
        val rewordList = dto.reward.map {
            Reward(
                idx = 0L,
                crowdfundingIdx = crowdfundingIdx,
                title = it.title,
                description = it.description,
                price = it.price,
                totalCount = it.totalCount,
                imageList = it.imageList
            )
        }
        commandRewordPort.saveAllReword(rewordList)

        // 정상적으로 생성이 되면 crowdfunding을 각 서비스에 publish 해준다.
        crowdfundingPublishPort.create(crowdfunding)
    }

}