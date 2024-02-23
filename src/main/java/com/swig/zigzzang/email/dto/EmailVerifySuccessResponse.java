package com.swig.zigzzang.email.dto;

public record EmailVerifySuccessResponse(String message) {

    public static EmailVerifySuccessResponse of() {
        return new EmailVerifySuccessResponse("이메일 인증이 성공적으로 완료되었습니다.");
    }
}
