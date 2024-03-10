package com.swig.zigzzang.calender.dto.request.WaterIntake;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record WaterIntakeUpdateRequest(

        @NotNull(message = "목표 섭취량을 입력해 주세요")
        @Schema(description = "목표 섭취량", nullable = false, example = "2000")
        Long waterRequirement, // 목표 섭취량

        @NotNull(message = "하루 섭취 횟수를 입력해 주세요")
        @Schema(description = "하루 물 섭취 횟수", nullable = false, example = "4")
        Long waterFrequency,// 물 1일 섭취 횟수

        @NotNull(message = "1회 섭취량을 입력해 주세요")
        @Schema(description = "물 1회 섭취량", nullable = false, example = "500")
        Long waterCapacity // 물 1회 섭취량
) {


}
