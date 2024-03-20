package com.swig.zigzzang.calender.dto.util;

import com.swig.zigzzang.calender.domain.Schedule;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
public class ScheduleDTO {

    @Schema(description = "스케쥴 ID", nullable = false, example = "1")
    Long scheduleId;

    @Schema(description = "스케쥴 이름", nullable = false, example = "1")
    String scheduleName; // 일정명

    @Schema(description = "스케쥴 날짜", nullable = false, example = "1")
    String scheduleDate;

    @Schema(description = "스케쥴 시간", nullable = false, example = "")
    String scheduleTime;


    // entity -> dto
    public static ScheduleDTO of(Schedule schedule) {
        return ScheduleDTO.builder()
                .scheduleId(schedule.getScheduleId())
                .scheduleName(schedule.getName())
                .scheduleDate(schedule.getDate())
                .scheduleTime(schedule.getTime())
                .build();
    }

    // entityList -> dtoList
    public static List<ScheduleDTO> ofList(List<Schedule> scheduleList) {
        return scheduleList.stream()
                .map(ScheduleDTO::of)
                .collect(Collectors.toList());
    }
}
