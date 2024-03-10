package com.swig.zigzzang.calender.dto.request.WaterIntake;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.swig.zigzzang.calender.domain.Calender;
import com.swig.zigzzang.calender.domain.WaterIntake;
import com.swig.zigzzang.member.domain.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;

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
        Long waterCapacity, // 물 1회 섭취량

        @NotNull(message = "캘린더 날짜를 입력해 주세요")
        @Schema(description = "캘린더 날짜", nullable = false, example = "2024-03-10")
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate calenderDate // 캘린더 날짜
) {

    public WaterIntake toEntity(Member member, Calender calender) {
        return WaterIntake.builder()
                .calender(calender)
                .requirement(waterRequirement)
                .frequency(waterFrequency)
                .capacity(waterCapacity)
                .calenderDate(calenderDate.toString())
                .achievement(0L) // 초기 성취도 0
                .build();

    }
}
