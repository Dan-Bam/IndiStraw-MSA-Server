package com.project.indistraw.account.domain

data class Authentication(
    val phoneNumber: String,
    val authCodeCount: Long,
    val authenticationCount: Long,
    val isVerified: Boolean,
    val expiredAt: Long
) {

    constructor(phoneNumber: String): this(
        phoneNumber = phoneNumber,
        authCodeCount = 0,
        authenticationCount = 0,
        isVerified = false,
        expiredAt = EXPIRED_AT
    )

    companion object {
        const val EXPIRED_AT = 7200L
    }

    fun increaseAuthCodeCount(): Authentication =
        this.copy(
            authCodeCount = authCodeCount.inc()
        )

    fun increaseAuthenticationCount(): Authentication =
        this.copy(
            authenticationCount = authenticationCount.inc()
        )

    fun certified(): Authentication =
        this.copy(
            isVerified = true,
        )

}