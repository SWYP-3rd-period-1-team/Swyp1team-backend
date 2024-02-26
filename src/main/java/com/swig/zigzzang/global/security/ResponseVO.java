package com.swig.zigzzang.global.security;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ResponseVO {
    private final HttpStatus status;
    private final String message;
    private final String code;
}
