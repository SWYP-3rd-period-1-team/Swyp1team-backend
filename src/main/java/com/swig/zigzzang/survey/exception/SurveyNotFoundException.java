package com.swig.zigzzang.survey.exception;

import com.swig.zigzzang.global.exception.HttpExceptionCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter
@Setter
public class SurveyNotFoundException extends RuntimeException{
    private final HttpStatus httpStatus;

    public SurveyNotFoundException(HttpExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.httpStatus = exceptionCode.getHttpStatus();
    }

    public SurveyNotFoundException() {
        this(HttpExceptionCode.SURVERY_NOT_FOUND);
    }
}
