package com.swig.zigzzang.member.dto;

import lombok.Builder;

@Builder
public record FindIdResponse(String userId) {

    public static FindIdResponse of(String userId) {
        FindIdResponse response=
                FindIdResponse.builder()
                        .userId(userId)
                        .build();
        return response;
    }
}
