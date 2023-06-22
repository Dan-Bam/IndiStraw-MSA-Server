package com.project.indistraw.crowdfunding.application.common.error

enum class ErrorCode(
    val message: String,
    val status: Int
) {

    // CROWDFUNDING
    CROWDFUNDING_NOT_FOUND("크라우드 펀딩을 찾을 수 없습니다.", 404),

    // TOKEN
    INVALID_TOKEN_TYPE("유효하지 않은 토큰 타입 입니다.", 401),

}