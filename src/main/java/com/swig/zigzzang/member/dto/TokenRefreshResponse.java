package com.swig.zigzzang.member.dto;

public record TokenRefreshResponse(String accessToken) {
    public static TokenRefreshResponse of(String accessToken) {
        return new TokenRefreshResponse(accessToken);
    }
}
