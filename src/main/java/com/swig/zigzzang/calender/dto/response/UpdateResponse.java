package com.swig.zigzzang.calender.dto.response;

import lombok.Builder;

@Builder
public record UpdateResponse(
        String message
) {
    public static UpdateResponse of() {
        return UpdateResponse.builder()
                .message("정보가 수정되었습니다.")
                .build();

    }
}
