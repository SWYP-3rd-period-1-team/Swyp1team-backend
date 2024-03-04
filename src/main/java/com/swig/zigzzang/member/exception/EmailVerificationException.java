package com.swig.zigzzang.member.exception;

import com.swig.zigzzang.global.exception.HttpExceptionCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter
@Setter
public class EmailVerificationException extends RuntimeException{
    private final HttpStatus httpStatus;

    public EmailVerificationException(HttpExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.httpStatus = exceptionCode.getHttpStatus();
    }

    public EmailVerificationException() {
        this(HttpExceptionCode.EMAIL_NOT_FOUND);
    }
}
