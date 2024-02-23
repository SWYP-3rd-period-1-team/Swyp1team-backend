package com.swig.zigzzang.email.exception;

import com.swig.zigzzang.global.exception.HttpExceptionCode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Slf4j
public class EmailNotSendException extends RuntimeException{
    private final HttpStatus httpStatus;

    public EmailNotSendException(HttpExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.httpStatus = exceptionCode.getHttpStatus();
    }
}
