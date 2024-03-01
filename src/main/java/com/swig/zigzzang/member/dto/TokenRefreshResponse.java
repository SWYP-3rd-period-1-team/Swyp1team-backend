package com.swig.zigzzang.member.dto;

import lombok.Builder;

@Builder
public record TokenRefreshResponse(String accessToken,String refreshToken) {
    public static TokenRefreshResponse of(String accessToken,
                                          String refreshToken
    ) {
        return TokenRefreshResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
