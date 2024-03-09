package com.swig.zigzzang.calender.dto.util;

import com.swig.zigzzang.calender.domain.WaterIntake;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WaterIntakeDTO {

    Long waterRequirement; // 목표 섭취량

    Long waterFrequency; // 물 1일 섭취 횟수

    Long waterCapacity; // 물 1회 섭취량

    Long achievement; // 진행률


    // entity -> dto
    public static WaterIntakeDTO of(WaterIntake waterIntake) {
        return WaterIntakeDTO.builder()
                .waterRequirement(waterIntake.getRequirement())
                .waterFrequency(waterIntake.getFrequency())
                .waterCapacity(waterIntake.getCapacity())
                .achievement(waterIntake.getAchievement())
                .build();
    }
}
