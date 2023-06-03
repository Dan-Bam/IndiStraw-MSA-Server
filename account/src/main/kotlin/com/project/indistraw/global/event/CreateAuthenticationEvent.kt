package com.project.indistraw.global.event

data class CreateAuthenticationEvent(
    val phoneNumber: String,
    val attemptCount: Long,
    val isVerified: Boolean,
    val expiredAt: Long
) {

    constructor(phoneNumber: String): this(
        phoneNumber = phoneNumber,
        attemptCount = 0,
        isVerified = false,
        expiredAt = EXPIRED_AT
    )

    companion object {
        const val MAX_ATTEMPT_COUNT = 5L
        const val EXPIRED_AT = 1800L
        private const val VERIFIED_EXPIRED_AT = 2700L
    }

    fun increaseCount(): CreateAuthenticationEvent {
        return CreateAuthenticationEvent(
            phoneNumber = phoneNumber,
            attemptCount = attemptCount.inc(),
            isVerified = isVerified,
            expiredAt = expiredAt
        )
    }

    fun certified(): CreateAuthenticationEvent =
        this.copy(
            phoneNumber = phoneNumber,
            attemptCount = MAX_ATTEMPT_COUNT,
            isVerified = true,
            expiredAt = VERIFIED_EXPIRED_AT
        )

}
