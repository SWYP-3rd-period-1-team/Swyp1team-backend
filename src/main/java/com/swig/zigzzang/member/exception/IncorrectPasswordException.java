package com.swig.zigzzang.member.exception;

import com.swig.zigzzang.global.exception.HttpExceptionCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class IncorrectPasswordException extends RuntimeException{
    private final HttpStatus httpStatus;

    public IncorrectPasswordException(HttpExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.httpStatus = exceptionCode.getHttpStatus();
    }

    public IncorrectPasswordException() {
        this(HttpExceptionCode.INCORRECT_PASSWORD);
    }
}
