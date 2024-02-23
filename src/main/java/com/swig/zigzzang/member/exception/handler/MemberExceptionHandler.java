package com.swig.zigzzang.member.exception.handler;

import com.swig.zigzzang.global.response.ErrorResponse;
import com.swig.zigzzang.global.response.HttpResponse;
import com.swig.zigzzang.member.exception.MemberExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MemberExceptionHandler {
    @ExceptionHandler(MemberExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public HttpResponse<ErrorResponse> memberExistExceptionHandler(MemberExistException e) {
        return HttpResponse.status(e.getHttpStatus())
                .body(ErrorResponse.from(e.getHttpStatus(), e.getMessage()));
    }
}
