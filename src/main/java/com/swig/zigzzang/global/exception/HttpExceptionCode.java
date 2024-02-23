package com.swig.zigzzang.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum HttpExceptionCode {
    UNABLE_TO_SEND_EMAIL(HttpStatus.BAD_REQUEST, "이메일 전송에 실패했습니다."),
    MEMBER_EXISTS(HttpStatus.CONFLICT, "이미 존재하는 회원입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
