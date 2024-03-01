package com.swig.zigzzang.hospital.exception;

import com.swig.zigzzang.global.exception.HttpExceptionCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CommentUpdateAuthException extends RuntimeException {

    private final HttpStatus httpStatus;


    public CommentUpdateAuthException(HttpExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.httpStatus = exceptionCode.getHttpStatus();
    }


    public CommentUpdateAuthException() {
        this(HttpExceptionCode.COMMENT_UPDATE_UNAUTHORIZED);
    }
}
