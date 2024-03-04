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
    INCORRECT_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "올바르지 않은 리프레시 토큰입니다. 기한이 만료되었거나, 이미 로그아웃이 완료되어 DB에 존재하지 않는 상태입니다."),
    EXPIRED_TOKEN(HttpStatus.BAD_REQUEST,"만료된 토큰입니다. 토큰을 재발급하세요"),
    WRONG_TYPE_TOKEN(HttpStatus.UNAUTHORIZED,"토큰의 정보가 임의로 변경되었습니다."),
    UNKNOWN_TOKEN(HttpStatus.UNAUTHORIZED,"인증 토큰이 존재하지 않습니다."),
    UNSUPPORTED_TOKEN(HttpStatus.UNAUTHORIZED,"토큰의 길이나 형식이 올바르지 않습니다."),
    HEADER_NOT_FOUND(HttpStatus.UNAUTHORIZED,"Authorization 헤더 정보가 존재하지 않습니다."),
    BEARER_NOT_FOUND(HttpStatus.UNAUTHORIZED,"Bearer 로 Authorization 헤더가 시작되지 않습니다."),
    EMAIL_FAILED(HttpStatus.UNAUTHORIZED,"이메일 인증 코드가 올바르지 않습니다."),
    EMAIL_USER_NOTFOUND(HttpStatus.NOT_FOUND, "해당하는 이메일의 사용자가 존재하지 않습니다."),
    EMAIL_USERID_USER_NOT(HttpStatus.NOT_FOUND, "해당하는 이메일, 아이디에 일치하는 사용자를 찾을수 없습니다."),

    HOSPITAL_NOT_EXIST(HttpStatus.NOT_FOUND, "해당 병원이 존재하지 않습니다."),
    COMMENT_NOT_EXIST(HttpStatus.NOT_FOUND,"해당 댓글이 존재하지 않습니다."),


    INCORRECT_PASSWORD(HttpStatus.UNAUTHORIZED,"비밀번호 확인과 새로운 비밀번호가 일치하지 않습니다."),

    COMMENT_UPDATE_UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"수정 권한이 없습니다."),

    EMAIL_NOT_FOUND(HttpStatus.NOT_FOUND,"사용자의 이메일을 찾을수 없습니다."),
    SURVERY_NOT_FOUND(HttpStatus.NOT_FOUND,"해당 Surveyid의 질병설문을 찾을수 없습니다."),
    SURVERY_MEMBER_NOT_EQUAL(HttpStatus.CONFLICT,"해당 Surveyid의 질병설문을 찾을수 없습니다.");



    private final HttpStatus httpStatus;
    private final String message;
}
