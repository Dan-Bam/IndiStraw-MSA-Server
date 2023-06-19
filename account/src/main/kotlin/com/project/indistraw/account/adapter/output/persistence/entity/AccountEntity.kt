package com.project.indistraw.account.adapter.output.persistence.entity

import com.project.indistraw.account.domain.Authority
import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "account")
class AccountEntity(
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name = "account_idx", columnDefinition = "BINARY(16)", nullable = false)
    val accountIdx: UUID,

    @Column(nullable = false, length = 20)
    val id: String,

    @Column(nullable = false, length = 60)
    val encodedPassword: String,

    @Column(nullable = false, length = 10)
    val name: String,

    @Column(nullable = false, length = 15)
    val phoneNumber: String,

    @Embedded
    val address: Address?,

    @Column(nullable = true, columnDefinition = "TEXT")
    var profileUrl: String?,

    @Enumerated(EnumType.STRING)
    val authority: Authority
)