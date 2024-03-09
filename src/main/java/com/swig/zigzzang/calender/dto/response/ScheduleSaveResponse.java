package com.swig.zigzzang.calender.dto.response;

import lombok.Builder;

@Builder
public record ScheduleSaveResponse(
        String message
) {
    public static ScheduleSaveResponse of() {
        return ScheduleSaveResponse.builder()
                .message("일정이 저장되었습니다.")
                .build();

    }
}
