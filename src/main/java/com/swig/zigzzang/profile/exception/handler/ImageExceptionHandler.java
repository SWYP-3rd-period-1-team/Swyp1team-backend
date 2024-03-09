package com.swig.zigzzang.profile.exception.handler;

import com.swig.zigzzang.global.response.ErrorResponse;
import com.swig.zigzzang.profile.exception.ImageUploadException;
import com.swig.zigzzang.survey.exception.SurveyNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ImageExceptionHandler {
    @ExceptionHandler(ImageUploadException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> imageUploadFailedExceptionHandler(ImageUploadException e) {
        return ResponseEntity.status(e.getHttpStatus())
                .body(ErrorResponse.from(e.getHttpStatus(), e.getMessage()));
    }

}
