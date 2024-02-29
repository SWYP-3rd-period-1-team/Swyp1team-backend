package com.swig.zigzzang.hospital.exception;

import com.swig.zigzzang.global.exception.HttpExceptionCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class HospitalNotExistException extends RuntimeException {

    private final HttpStatus httpStatus;


    public HospitalNotExistException(HttpExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.httpStatus = exceptionCode.getHttpStatus();
    }


    public HospitalNotExistException() {
        this(HttpExceptionCode.HOSPITAL_NOT_EXIST);
    }
}
