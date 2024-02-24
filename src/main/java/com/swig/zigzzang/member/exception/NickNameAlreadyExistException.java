package com.swig.zigzzang.member.exception;

import com.swig.zigzzang.global.exception.HttpExceptionCode;
import org.springframework.http.HttpStatus;

public class NickNameAlreadyExistException extends RuntimeException{
    private final HttpStatus httpStatus;

    public NickNameAlreadyExistException(HttpExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.httpStatus = exceptionCode.getHttpStatus();
    }

    public NickNameAlreadyExistException() {
        this(HttpExceptionCode.NICKNAME_EXIST);
    }
}
