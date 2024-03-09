package com.swig.zigzzang.calender.excepiton;

import com.swig.zigzzang.global.exception.HttpExceptionCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CalenderNotExistException extends RuntimeException {

    private final HttpStatus httpStatus;


    public CalenderNotExistException(HttpExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.httpStatus = exceptionCode.getHttpStatus();
    }


    public CalenderNotExistException() {
        this(HttpExceptionCode.HOSPITAL_NOT_EXIST);
    }
}
