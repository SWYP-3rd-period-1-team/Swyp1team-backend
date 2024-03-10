package com.swig.zigzzang.calender.excepiton;

import com.swig.zigzzang.global.exception.HttpExceptionCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UpdateException extends RuntimeException {

    private final HttpStatus httpStatus;


    public UpdateException(HttpExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.httpStatus = exceptionCode.getHttpStatus();
    }


    public UpdateException() {
        this(HttpExceptionCode.UPDATE_FAILED);
    }
}
