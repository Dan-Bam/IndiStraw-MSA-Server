package com.project.indistraw.global.error

enum class ErrorCode(
    val message: String,
    val status: Int
) {

    // PAY
    PAY_CONFIRM_FAILED("결제 정보 검증에 실패하였습니다.", 400),

}