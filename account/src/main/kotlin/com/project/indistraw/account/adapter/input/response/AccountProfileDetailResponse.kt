package com.project.indistraw.account.adapter.input.response

data class AccountProfileDetailResponse(
    val id: String,
    val name: String,
    val phoneNumber: String,
    val address: String?,
    val profileUrl: String?,
)