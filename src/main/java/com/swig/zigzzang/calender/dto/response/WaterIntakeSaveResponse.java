package com.swig.zigzzang.calender.dto.response;

import lombok.Builder;

@Builder
public record WaterIntakeSaveResponse(
        String message
) {
    public static WaterIntakeSaveResponse of() {
        return WaterIntakeSaveResponse.builder()
                .message("물 섭취 정보가 저장되었습니다.")
                .build();

    }
}
