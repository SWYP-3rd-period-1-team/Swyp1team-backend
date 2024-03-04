package com.swig.zigzzang.survey.exception;

import com.swig.zigzzang.global.exception.HttpExceptionCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter
@Setter
public class SurveyMemberNotEqualException extends RuntimeException{
    private final HttpStatus httpStatus;

    public SurveyMemberNotEqualException(HttpExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.httpStatus = exceptionCode.getHttpStatus();
    }

    public SurveyMemberNotEqualException() {
        this(HttpExceptionCode.SURVERY_NOT_FOUND);
    }
}
