package com.project.indistraw.global.error

enum class ErrorCode(
    val message: String,
    val status: Int
) {

    // FUNDING
    FUNDING_NOT_FOUND("펀딩을 찾을 수 없습니다.", 404),

    // PAY
    FAILED_PAY_CONFIRM("결제 정보 검증에 실패하였습니다.", 400),
    FAILED_PAY_CANCEL("결제 취소에 실패하였습니다.", 400),

}