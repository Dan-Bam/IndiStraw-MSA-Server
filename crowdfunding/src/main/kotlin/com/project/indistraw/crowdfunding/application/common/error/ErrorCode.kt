package com.project.indistraw.crowdfunding.application.common.error

enum class ErrorCode(
    val message: String,
    val status: Int
) {

    // CROWDFUNDING
    CROWDFUNDING_NOT_FOUND("크라우드 펀딩을 찾을 수 없습니다.", 404),

    // FUNDING
    FUNDING_NOT_FOUND("펀딩을 찾을 수 없습니다.", 404),

    // REWARD
    REWARD_NOT_FOUND("리워드를 찾을 수 없습니다.", 404),

    // ACCOUNT
    ACCOUNT_NOT_FOUND("계정을 찾을 수 없습니다.", 404),

    // PAY
    FAILED_PAY_CONFIRM("결제 정보 검증에 실패하였습니다.", 400),
    FAILED_PAY_CANCEL("결제 취소에 실패하였습니다.", 400),
    RECEIPT_ID_NOT_FOUND("존재하지 않은 receiptId 입니다.", 404),

    // FEIGN
    FEIGN_BAD_REQUEST("Feign Bad Request", 400),
    FEIGN_UNAUTHORIZED("Feign Unauthorized", 401),
    FEIGN_FORBIDDEN("Feign Forbidden", 403),
    FEIGN_NOT_FOUND("Feign Not Found", 404)

}