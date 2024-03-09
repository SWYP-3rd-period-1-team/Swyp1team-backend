package com.swig.zigzzang.calender.dto.response;

import lombok.Builder;

@Builder
public record SleepScheduleSaveResponse(
        String message
) {
    public static SleepScheduleSaveResponse of() {
        return SleepScheduleSaveResponse.builder()
                .message("수면 시간이 저장되었습니다.")
                .build();

    }
}
