package com.project.indistraw.crowdfunding.adapter.output.persistence.common

interface GenericConverter<D, E> {

    infix fun toEntity(domain: D): E

    infix fun toDomain(entity: E?): D?

}