package com.project.indistraw.crowdfunding.adapter.output.persistence.entity

import com.project.indistraw.crowdfunding.adapter.output.persistence.common.entity.BaseTimeEntity
import org.hibernate.annotations.SQLDelete
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "account")
@SQLDelete(sql = "update account set deleted_at = CURRENT_TIMESTAMP where crowdfunding.account.account_idx = ?")
class AccountEntity(
    @Id
    @Column(name = "account_idx", columnDefinition = "BINARY(16)", nullable = false)
    val accountIdx: UUID,

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
): BaseTimeEntity()