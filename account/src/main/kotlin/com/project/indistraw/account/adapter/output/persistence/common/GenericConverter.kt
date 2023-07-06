package com.project.indistraw.account.adapter.output.persistence.common

interface GenericConverter<D, E> {

    fun toEntity(domain: D): E

    fun toDomain(entity: E?): D?


}