package com.swig.zigzzang.global.response;

import com.swig.zigzzang.global.exception.HttpExceptionCode;
import org.springframework.http.HttpStatus;

public record ErrorResponse(String success, String message) {
    public static ErrorResponse from(HttpExceptionCode exceptionCode) {
        return new ErrorResponse("false", exceptionCode.getMessage());
    }

    public static ErrorResponse from(HttpExceptionCode exceptionCode, String errorMessage) {
        return new ErrorResponse("false", errorMessage);
    }

    public static ErrorResponse from(HttpStatus httpStatus, String errorMessage) {
        return new ErrorResponse("false", errorMessage);
    }

}
