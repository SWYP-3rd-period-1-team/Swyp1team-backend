package com.swig.zigzzang.member.dto;

import lombok.Builder;

@Builder
public record ChangePasswordResponse(String message) {
    public static ChangePasswordResponse of(String newpassword) {
        return ChangePasswordResponse.builder()
                .message(newpassword+" 으로 비밀보를 변경하였습니다.")
                .build();
    }
}
