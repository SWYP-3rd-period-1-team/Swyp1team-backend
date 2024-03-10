package com.swig.zigzzang.calender.excepiton;

import com.swig.zigzzang.global.exception.HttpExceptionCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class DeleteException extends RuntimeException {

    private final HttpStatus httpStatus;


    public DeleteException(HttpExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.httpStatus = exceptionCode.getHttpStatus();
    }


    public DeleteException() {
        this(HttpExceptionCode.DELETE_FAILED);
    }
}
