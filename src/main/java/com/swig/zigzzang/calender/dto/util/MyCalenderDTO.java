package com.swig.zigzzang.calender.dto.util;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class MyCalenderDTO {

    @Schema(description = "캘린더 번호", nullable = false, example = "1")
    private Long calenderId; // 캘린더 번호

    @Schema(description = "해당 날짜 수면 스케쥴 정보", nullable = false, example = "")
    private SleepScheduleDTO sleepScheduleInfo; // 수면 스케줄 정보

    @Schema(description = "해당 날짜 스케줄", nullable = false, example = "")
    private List<ScheduleDTO> scheduleInfoList; //  스케쥴 목록

    @Schema(description = "해당 날짜 영양제 정보", nullable = false, example = "")
    private List<SupplementDTO> supplementInfoList; // 영양제 목록

    @Schema(description = "해당 날짜 물 섭취 정보", nullable = false, example = "")
    private WaterIntakeDTO waterIntakeInfo; // 물 섭취 정보

}
