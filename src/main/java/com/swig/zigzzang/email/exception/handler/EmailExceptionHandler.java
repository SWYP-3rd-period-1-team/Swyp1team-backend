package com.swig.zigzzang.email.exception.handler;

import com.swig.zigzzang.email.exception.EmailNotSendException;
import com.swig.zigzzang.global.response.ErrorResponse;
import com.swig.zigzzang.global.response.HttpResponse;
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
public class EmailExceptionHandler {
    @ExceptionHandler(EmailNotSendException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpResponse<ErrorResponse> EmailnotSendExceptionHandler(EmailNotSendException e) {
        return HttpResponse.status(e.getHttpStatus())
                .body(ErrorResponse.from(e.getHttpStatus(), e.getMessage()));
    }
}
