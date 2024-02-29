package com.swig.zigzzang.email.dto;

import lombok.Builder;

@Builder
public record LoginRequest( String username, String password) {
}
