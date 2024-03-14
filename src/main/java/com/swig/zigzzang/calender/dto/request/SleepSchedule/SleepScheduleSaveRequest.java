package com.swig.zigzzang.calender.dto.request.SleepSchedule;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.swig.zigzzang.calender.domain.Calender;
import com.swig.zigzzang.calender.domain.SleepSchedule;
import com.swig.zigzzang.calender.domain.WaterIntake;
import com.swig.zigzzang.member.domain.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record SleepScheduleSaveRequest(

        @NotNull(message = "수면 기간을 입력해 주세요")
        @Schema(description = "수면 기간", nullable = false, example = "24000700")
        String sleepPeriod, // 수면 기간

        @NotNull(message = "수면 시간을 입력해 주세요")
        @Schema(description = "총 수면시간", nullable = false, example = "7")
        Long sleepTime, // 총 수면 시간

        @NotNull(message = "캘린더 날짜를 입력해 주세요")
        @Schema(description = "캘린더 날짜", nullable = false, example = "2024-03-10")
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate calenderDate // 캘린더 날짜
) {

    public SleepSchedule toEntity(Calender calender) {
        return SleepSchedule.builder()
                .calender(calender)
                .period(Long.valueOf(sleepPeriod))
                .time(sleepTime)
                .calenderDate(calenderDate.toString())
                .achieveArray(null)
                .build();

    }
}
