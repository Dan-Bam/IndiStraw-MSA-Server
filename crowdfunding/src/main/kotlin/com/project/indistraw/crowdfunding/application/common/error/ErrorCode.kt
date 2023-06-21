package com.project.indistraw.crowdfunding.application.common.error

enum class ErrorCode(
    val message: String,
    val status: Int
) {

    // CROWDFUNDING

    // TOKEN
    INVALID_TOKEN_TYPE("유효하지 않은 토큰 타입 입니다.", 401),

}