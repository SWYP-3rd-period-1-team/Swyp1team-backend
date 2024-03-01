package com.swig.zigzzang.member.exception;

import com.swig.zigzzang.global.exception.HttpExceptionCode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Slf4j
public class CommentExistException extends RuntimeException{
    private final HttpStatus httpStatus;

    public CommentExistException(HttpExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.httpStatus = exceptionCode.getHttpStatus();
    }

    public CommentExistException() {
        this(HttpExceptionCode.COMMENT_NOT_EXIST);
    }


}
