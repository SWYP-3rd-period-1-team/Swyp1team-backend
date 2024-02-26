package com.swig.zigzzang.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum HttpExceptionCode {
    UNABLE_TO_SEND_EMAIL(HttpStatus.BAD_REQUEST, "이메일 전송에 실패했습니다."),
    MEMBER_EXISTS(HttpStatus.CONFLICT, "이미 존재하는 회원입니다."),
    JWT_NOT_FOUND(HttpStatus.UNAUTHORIZED, "JWT를 찾을 수 없습니다."),
    REFRESH_TOKEN_NOT_FOUND(HttpStatus.UNAUTHORIZED, "리프레시 토큰을 찾을 수 없습니다."),
    INVALID_ARGUMENT(HttpStatus.BAD_REQUEST,  "올바르지 않은 값이 전달되었습니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."),
    NICKNAME_EXIST(HttpStatus.CONFLICT,"이미 존재하는 닉네임 입니다."),
    USERID_EXIST(HttpStatus.CONFLICT,"이미 존재하는 아이디입니다."),
    INCORRECT_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "올바르지 않은 리프레시 토큰입니다. 기한이 만료되었거나, 이미 로그아웃이 완료되어 DB에 존재하지 않는 상태입니다."),
    EXPIRED_TOKEN(HttpStatus.BAD_REQUEST,"만료된 토큰입니다. 토큰을 재발급하세요"),
    WRONG_TYPE_TOKEN(HttpStatus.UNAUTHORIZED,"토큰의 정보가 임의로 변경되었습니다."),
    UNKNOWN_TOKEN(HttpStatus.UNAUTHORIZED,"인증 토큰이 존재하지 않습니다."),
    UNSUPPORTED_TOKEN(HttpStatus.UNAUTHORIZED,"토큰의 길이나 형식이 올바르지 않습니다."),
    HEADER_NOT_FOUND(HttpStatus.UNAUTHORIZED,"Authorization 헤더 정보가 존재하지 않습니다."),
    BEARER_NOT_FOUND(HttpStatus.UNAUTHORIZED,"Bearer 로 Authorization 헤더가 시작되지 않습니다.");




    private final HttpStatus httpStatus;
    private final String message;
}
