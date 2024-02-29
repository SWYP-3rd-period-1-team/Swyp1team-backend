package com.swig.zigzzang.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum HttpExceptionCode {
    UNABLE_TO_SEND_EMAIL(HttpStatus.BAD_REQUEST, "이메일 전송에 실패했습니다."),
    MEMBER_EXISTS(HttpStatus.CONFLICT, "이미 존재하는 회원입니다."),
    MEMBER_NOT_EXISTS(HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다."),
    JWT_NOT_FOUND(HttpStatus.UNAUTHORIZED, "JWT를 찾을 수 없습니다."),
    REFRESH_TOKEN_NOT_FOUND(HttpStatus.UNAUTHORIZED, "리프레시 토큰을 찾을 수 없습니다."),
    INVALID_ARGUMENT(HttpStatus.BAD_REQUEST, "올바르지 않은 값이 전달되었습니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."),
    NICKNAME_EXIST(HttpStatus.CONFLICT, "이미 존재하는 닉네임 입니다."),
    USERID_EXIST(HttpStatus.CONFLICT, "이미 존재하는 아이디입니다."),

    // 병원 괸련 exceptionCode
    HOSPITAL_NOT_EXIST(HttpStatus.NOT_FOUND, "해당 병원이 존재하지 않습니다."),
    COMMENT_NOT_EXIST(HttpStatus.NOT_FOUND,"해당 댓글이 존재하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
