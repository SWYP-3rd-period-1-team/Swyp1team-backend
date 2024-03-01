package com.swig.zigzzang.profile.dto;

import lombok.Builder;

@Builder
public record ChangeProfileImageResponse(
        String message
) {
    public static ChangeProfileImageResponse of(String imageUrl) {
        return ChangeProfileImageResponse.builder()
                .message("프로필 이미지가 성공적으로 변경되었습니다."+imageUrl)
                .build();
    }
}
