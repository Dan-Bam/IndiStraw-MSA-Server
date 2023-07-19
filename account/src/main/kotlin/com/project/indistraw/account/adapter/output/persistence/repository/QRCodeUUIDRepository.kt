package com.project.indistraw.account.adapter.output.persistence.repository

import com.project.indistraw.account.adapter.output.persistence.entity.QRCodeUUIDEntity
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface QRCodeUUIDRepository: CrudRepository<QRCodeUUIDEntity, UUID>