package com.project.indistraw.global.event.account

import java.util.UUID

data class DeleteAccountPublishEvent(
    val accountIdx: UUID
)