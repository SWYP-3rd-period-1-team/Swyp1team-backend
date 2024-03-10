package com.swig.zigzzang.calender.dto.response.Supplement;

import lombok.Builder;

@Builder
public record SupplementUpdateResponse(
        String message
) {
    public static SupplementUpdateResponse of() {
        return SupplementUpdateResponse.builder()
                .message("영양제 정보가 저장되었습니다.")
                .build();

    }
}
