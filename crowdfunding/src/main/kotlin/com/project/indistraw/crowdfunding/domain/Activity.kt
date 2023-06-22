package com.project.indistraw.crowdfunding.domain

import com.project.indistraw.crowdfunding.application.common.annotation.Aggregate

@Aggregate
enum class Activity {

    UNDER_REVIEW, RECRUITING, FINISHED

}