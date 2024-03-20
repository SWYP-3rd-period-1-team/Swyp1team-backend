package com.swig.zigzzang.calender.dto.util;

import com.swig.zigzzang.calender.domain.WaterIntake;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WaterIntakeDTO {

    @Schema(description = "물 섭취 번호", nullable = false, example = "1")
    Long waterIntakeId;

    @Schema(description = "목표 섭취량", nullable = false, example = "2000")
    Long waterRequirement; // 목표 섭취량

    @Schema(description = "물 1일 섭취 횟수", nullable = false, example = "3")
    Long waterFrequency; // 물 1일 섭취 횟수

    @Schema(description = "물 1회 섭취량", nullable = false, example = "750")
    Long waterCapacity; // 물 1회 섭취량

    @Schema(description = "캘린더 날짜", nullable = false, example = "2024-03-10")
    String calenderDate;

    @Schema(description = "달성도 boolean 가변 배열", nullable = false, example = "[true,false,true,false]")
    Boolean [] supplementAchieveArray;


    // entity -> dto
    public static WaterIntakeDTO of(WaterIntake waterIntake) {
        return WaterIntakeDTO.builder()
                .waterIntakeId(waterIntake.getWaterIntakeId())
                .waterRequirement(waterIntake.getRequirement())
                .waterFrequency(waterIntake.getFrequency())
                .waterCapacity(waterIntake.getCapacity())
                .calenderDate(waterIntake.getCalenderDate())
                .supplementAchieveArray(waterIntake.getAchieveArray())
                .build();
    }
}
