package com.project.indistraw.crowdfunding.adapter.output.persistence.common.entity

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
class BaseIdxEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idx: Long
)