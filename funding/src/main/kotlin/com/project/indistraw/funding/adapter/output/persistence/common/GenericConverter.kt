package com.project.indistraw.funding.adapter.output.persistence.common

interface GenericConverter<D, E> {

    fun toEntity(domain: D): E

    fun toDomain(entity: E?): D?


}