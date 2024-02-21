package com.swig.zigzzang.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum HttpExceptionCode {;

    private final HttpStatus httpStatus;
    private final String message;
}
