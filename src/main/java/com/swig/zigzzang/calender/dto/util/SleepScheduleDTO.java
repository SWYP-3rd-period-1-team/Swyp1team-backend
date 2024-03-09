package com.swig.zigzzang.calender.dto.util;

import com.swig.zigzzang.calender.domain.SleepSchedule;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SleepScheduleDTO {

    Long sleepPeriod; // 수면 기간

    Long sleepTime; // 총 수면 시간

    Long achievement;  // 진행률


    // entity -> dto
    public static SleepScheduleDTO of(SleepSchedule sleepSchedule) {
        return SleepScheduleDTO.builder()
                .sleepPeriod(sleepSchedule.getPeriod())
                .sleepTime(sleepSchedule.getTime())
                .achievement(sleepSchedule.getAchievement())
                .build();

    }

}

