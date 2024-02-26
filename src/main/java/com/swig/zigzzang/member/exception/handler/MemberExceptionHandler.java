package com.swig.zigzzang.member.exception.handler;

import com.swig.zigzzang.global.response.ErrorResponse;
import com.swig.zigzzang.global.response.HttpResponse;
import com.swig.zigzzang.member.exception.EmailCodeFailedException;
import com.swig.zigzzang.member.exception.MemberExistException;
import com.swig.zigzzang.member.exception.MemberNotFoundException;
import com.swig.zigzzang.member.exception.NickNameAlreadyExistException;
import com.swig.zigzzang.member.exception.UserIdAlreadyExistException;
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
public class MemberExceptionHandler {
    @ExceptionHandler(MemberExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponse> memberExistExceptionHandler(MemberExistException e) {
        return ResponseEntity.status(e.getHttpStatus())
                .body(ErrorResponse.from(e.getHttpStatus(), e.getMessage()));
    }
    @ExceptionHandler(NickNameAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponse> nickNameAlreadyExistExceptionHandler(NickNameAlreadyExistException e) {
        return ResponseEntity.status(e.getHttpStatus())
                .body(ErrorResponse.from(e.getHttpStatus(), e.getMessage()));
    }
    @ExceptionHandler(UserIdAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponse> userIdAlreadyExistExceptionHandler(UserIdAlreadyExistException e) {
        return ResponseEntity.status(e.getHttpStatus())
                .body(ErrorResponse.from(e.getHttpStatus(), e.getMessage()));
    }
    @ExceptionHandler(MemberNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> memberNotFoundExceptionHandler(MemberNotFoundException e) {
        return ResponseEntity.status(e.getHttpStatus())
                .body(ErrorResponse.from(e.getHttpStatus(), e.getMessage()));
    }
    @ExceptionHandler(EmailCodeFailedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorResponse> emailCodeFailedExceptionHandler(EmailCodeFailedException e) {
        return ResponseEntity.status(e.getHttpStatus())
                .body(ErrorResponse.from(e.getHttpStatus(), e.getMessage()));
    }
}
