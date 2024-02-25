package com.swig.zigzzang.global.exception.custom.security;

import com.swig.zigzzang.global.exception.HttpExceptionCode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Slf4j
public class SecurityJwtNotFoundException extends RuntimeException {
    private final HttpStatus httpStatus;
    public SecurityJwtNotFoundException(HttpExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.httpStatus=exceptionCode.getHttpStatus();
    }


    public SecurityJwtNotFoundException(){
        this(HttpExceptionCode.JWT_NOT_FOUND);}
}