package com.swig.zigzzang.calender.dto.request.SleepSchedule;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record SleepScheduleAchieveUpdateRequest(

        @Schema(description = "수면 계획 달성 여부(단일 체크 박스)", nullable = false, example = "true")
        Boolean achievement // 달성 여부 배열
) {


}
