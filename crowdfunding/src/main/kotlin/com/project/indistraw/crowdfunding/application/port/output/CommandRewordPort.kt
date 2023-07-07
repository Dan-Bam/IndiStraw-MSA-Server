package com.project.indistraw.crowdfunding.application.port.output

import com.project.indistraw.crowdfunding.domain.Reward

interface CommandRewordPort {

    fun saveAllReword(rewordList: List<Reward>)

}