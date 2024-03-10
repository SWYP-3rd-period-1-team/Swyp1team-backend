package com.swig.zigzzang.calender.dto.response;

import lombok.Builder;

@Builder
public record DeleteResponse(
        String message
) {
    public static DeleteResponse of() {
        return DeleteResponse.builder()
                .message("정보가 삭제되었습니다.")
                .build();

    }
}
