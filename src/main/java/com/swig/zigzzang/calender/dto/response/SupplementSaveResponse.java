package com.swig.zigzzang.calender.dto.response;

import com.swig.zigzzang.hospital.dto.response.CommentDeleteResponse;
import lombok.Builder;

@Builder
public record SupplementSaveResponse(
        String message
) {
    public static SupplementSaveResponse of() {
        return SupplementSaveResponse.builder()
                .message("영양제 정보가 저장되었습니다.")
                .build();

    }
}
