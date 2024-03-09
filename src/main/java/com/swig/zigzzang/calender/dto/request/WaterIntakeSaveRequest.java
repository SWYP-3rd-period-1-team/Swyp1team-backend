package com.swig.zigzzang.calender.dto.request;

import com.swig.zigzzang.calender.domain.Calender;
import com.swig.zigzzang.calender.domain.WaterIntake;
import com.swig.zigzzang.member.domain.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record WaterIntakeSaveRequest(

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

    public WaterIntake toEntity(Member member, Calender calender) {
        return WaterIntake.builder()
                .calender(calender)
                .requirement(waterRequirement)
                .frequency(waterFrequency)
                .capacity(waterCapacity)
                .achievement(0L) // 초기 성취도 0
                .build();

    }
}
