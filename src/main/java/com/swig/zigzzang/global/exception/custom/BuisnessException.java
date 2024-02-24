package com.swig.zigzzang.global.exception.custom;

import com.swig.zigzzang.global.exception.HttpExceptionCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

public class BuisnessException {
    @Getter
    public class BusinessException extends RuntimeException {

        private final HttpStatus httpStatus;

        public BusinessException(HttpExceptionCode httpExceptionCode) {
            super(httpExceptionCode.getMessage());
            this.httpStatus = httpExceptionCode.getHttpStatus();
        }
    }

}
