package com.project.indistraw.crowdfunding.application.port.output

import com.project.indistraw.crowdfunding.domain.Reword

interface CommandRewordPort {

    fun saveAllReword(rewordList: List<Reword>)

}