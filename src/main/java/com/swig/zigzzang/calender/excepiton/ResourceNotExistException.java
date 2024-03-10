package com.swig.zigzzang.calender.excepiton;

import com.swig.zigzzang.global.exception.HttpExceptionCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResourceNotExistException extends RuntimeException {

    private final HttpStatus httpStatus;


    public ResourceNotExistException(HttpExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.httpStatus = exceptionCode.getHttpStatus();
    }


    public ResourceNotExistException() {
        this(HttpExceptionCode.CALENDER_NOT_EXIST);
    }
}
