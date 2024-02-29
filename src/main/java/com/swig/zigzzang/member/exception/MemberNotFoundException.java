package com.swig.zigzzang.member.exception;

import com.swig.zigzzang.global.exception.HttpExceptionCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MemberNotFoundException extends RuntimeException{
    private final HttpStatus httpStatus;

    public MemberNotFoundException(HttpExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.httpStatus = exceptionCode.getHttpStatus();
    }

    public MemberNotFoundException() {
        this(HttpExceptionCode.USER_NOT_FOUND);
    }

}
