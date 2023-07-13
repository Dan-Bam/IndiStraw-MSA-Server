package com.project.indistraw.crowdfunding.adapter.output.persistence.entity

import com.project.indistraw.crowdfunding.adapter.output.persistence.common.entity.BaseUUIDEntity
import org.hibernate.annotations.SQLDelete
import java.util.*
import javax.persistence.Column
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "account")
@SQLDelete(sql = "update account set deleted_at = CURRENT_TIMESTAMP where idx = ?")
class AccountEntity(
    @Column(name = "account_idx", columnDefinition = "BINARY(16)", nullable = false)
    override val accountIdx: UUID,

    @Column(nullable = false)
    val id: String,

    @Column(nullable = false, length = 10)
    val name: String,

    @Column(nullable = false, length = 15)
    val phoneNumber: String,

    @Embedded
    val address: AddressEntity?,

    @Column(nullable = true, columnDefinition = "TEXT")
    val profileUrl: String?
): BaseUUIDEntity(accountIdx)