package com.project.indistraw.crowdfunding.domain

import com.project.indistraw.crowdfunding.application.common.annotation.Aggregate

@Aggregate
enum class StatusType {

    UNDER_REVIEW, RECRUITING, FINISHED

}