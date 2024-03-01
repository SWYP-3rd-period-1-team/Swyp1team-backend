package com.swig.zigzzang.member.dto;

import lombok.Builder;

@Builder
public record ChangeNicknameResponse(
        String message
) {
    public static ChangeNicknameResponse of(String newNickname) {
        return ChangeNicknameResponse.builder()
                .message("닉네임이 " + newNickname + " 으로 변경되었습니다.")
                .build();
    }
}
