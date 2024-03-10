package com.swig.zigzzang.calender.dto.response;

import lombok.Builder;

@Builder
public record SaveResponse(
        String message
) {
    public static SaveResponse of() {
        return SaveResponse.builder()
                .message("정보가 저장되었습니다.")
                .build();

    }
}
