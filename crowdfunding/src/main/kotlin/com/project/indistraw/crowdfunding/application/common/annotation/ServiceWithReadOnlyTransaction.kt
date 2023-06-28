package com.project.indistraw.crowdfunding.application.common.annotation

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Service
@Transactional(readOnly = true, rollbackFor = [Exception::class])
annotation class ServiceWithReadOnlyTransaction