package com.project.indistraw.crowdfunding.application.common.error

enum class ErrorCode(
    val message: String,
    val status: Int
) {

    // CROWDFUNDING
    CROWDFUNDING_NOT_FOUND("크라우드 펀딩을 찾을 수 없습니다.", 404),

    // FEIGN
    FEIGN_BAD_REQUEST("Feign Bad Request", 400),
    FEIGN_UNAUTHORIZED("Feign Unauthorized", 401),
    FEIGN_FORBIDDEN("Feign Forbidden", 403),
    FEIGN_NOT_FOUND("Feign Not Found", 404)

}