package com.swig.zigzzang.calender.dto.util;

import com.swig.zigzzang.calender.domain.Schedule;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
public class ScheduleDTO {

    String scheduleName; // 일정명

    String scheduleDate; // 총 수면 시간

    String scheduleTime; // 총 수면 시간

    Long achievement; // 진행률


    // entity -> dto
    public static ScheduleDTO of(Schedule schedule) {
        return ScheduleDTO.builder()
                .scheduleName(schedule.getName())
                .scheduleDate(schedule.getDate())
                .scheduleTime(schedule.getTime())
                .achievement(schedule.getAchievement())
                .build();
    }

    // entityList -> dtoList
    public static List<ScheduleDTO> ofList(List<Schedule> scheduleList) {
        return scheduleList.stream()
                .map(ScheduleDTO::of)
                .collect(Collectors.toList());
    }
}
