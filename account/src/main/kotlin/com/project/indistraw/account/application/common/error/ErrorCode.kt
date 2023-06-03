package com.project.indistraw.account.application.common.error

enum class ErrorCode(
    val message: String,
    val status: Int
) {

    // ACCOUNT
    DUPLICATE_PHONE_NUMBER("중복된 전화번호 입니다.", 409),
    DUPLICATE_ACCOUNT_ID("중복된 id 입니다.", 409),
    PASSWORD_NOT_MATCH("비밀번호가 일치하지 않습니다.", 400),
    ACCOUNT_NOT_FOUND("계정을 찾을 수 없습니다.", 404),

    // AUTH CODE
    AUTH_CODE_NOT_MATCH("인증 코드가 일치 하지 않습니다.", 400),

    // TOKEN
    INVALID_TOKEN("유효하지 않은 토큰입니다.", 401),
    INVALID_TOKEN_TYPE("유효하지 않은 토큰 타입 입니다.", 401),
    EXPIRED_ACCESS_TOKEN("만료된 accessToken 입니다.", 401),
    EXPIRED_REFRESH_TOKEN("만료된 refreshToken 입니다.", 401),

}