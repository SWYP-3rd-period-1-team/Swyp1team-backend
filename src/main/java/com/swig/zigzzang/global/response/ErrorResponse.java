package com.swig.zigzzang.global.response;

import com.swig.zigzzang.global.exception.HttpExceptionCode;
import org.springframework.http.HttpStatus;

public record ErrorResponse(HttpStatus errCode, String message) {
    public static ErrorResponse from(HttpExceptionCode exceptionCode) {
        return new ErrorResponse(exceptionCode.getHttpStatus(), exceptionCode.getMessage());
    }

    public static ErrorResponse from(HttpExceptionCode exceptionCode, String errorMessage) {
        return new ErrorResponse(exceptionCode.getHttpStatus(), errorMessage);
    }

    public static ErrorResponse from(HttpStatus httpStatus, String errorMessage) {
        return new ErrorResponse(httpStatus, errorMessage);
    }

}
