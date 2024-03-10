package com.swig.zigzzang.calender.dto.request.SleepSchedule;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record SleepScheduleUpdateRequest(

        @NotNull(message = "수면 기간을 입력해 주세요")
        @Schema(description = "수면 기간", nullable = false, example = "24000700")
        Long sleepPeriod, // 수면 기간

        @NotNull(message = "수면 시간을 입력해 주세요")
        @Schema(description = "총 수면시간", nullable = false, example = "7")
        Long sleepTime // 총 수면 시간

) {

}
