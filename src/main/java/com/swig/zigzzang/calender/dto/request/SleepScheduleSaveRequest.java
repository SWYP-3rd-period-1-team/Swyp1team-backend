package com.swig.zigzzang.calender.dto.request;

import com.swig.zigzzang.calender.domain.Calender;
import com.swig.zigzzang.calender.domain.SleepSchedule;
import com.swig.zigzzang.calender.domain.WaterIntake;
import com.swig.zigzzang.member.domain.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record SleepScheduleSaveRequest(

        @NotNull(message = "수면 기간을 입력해 주세요")
        @Schema(description = "수면 기간", nullable = false, example = "24000700")
        Long sleepPeriod, // 수면 기간

        @NotNull(message = "수면 시간을 입력해 주세요")
        @Schema(description = "총 수면시간", nullable = false, example = "7")
        Long sleepTime // 총 수면 시간
) {

    public SleepSchedule toEntity(Calender calender) {
        return SleepSchedule.builder()
                .calender(calender)
                .period(sleepPeriod)
                .time(sleepTime)
                .achievement(0L) // 초기 성취도 0
                .build();

    }
}
