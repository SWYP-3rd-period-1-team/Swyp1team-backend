package com.swig.zigzzang.calender.dto.request.Schedule;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.swig.zigzzang.calender.domain.Calender;
import com.swig.zigzzang.calender.domain.Schedule;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
public record ScheduleSaveRequest(

        @NotNull(message = "일정명을 입력해 주세요")
        @Schema(description = "일정명", nullable = false, example = "한의원 물리치료")
        String scheduleName, // 일정명

        @NotNull(message = "일정 날짜를 입력해 주세요")
        @Schema(description = "일정 날짜", nullable = false, example = "2024-03-09")
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate scheduleDate, // 총 수면 시간

        @NotNull(message = "일정 시간을 입력해 주세요")
        @Schema(description = "일정 시간", nullable = false, example = "13:00")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
        LocalTime scheduleTime, // 총 수면 시간

        @NotNull(message = "캘린더 날짜를 입력해 주세요")
        @Schema(description = "캘린더 날짜", nullable = false, example = "2024-03-10")
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate calenderDate // 캘린더 날짜
) {

    public Schedule toEntity(Calender calender) {
        return Schedule.builder()
                .calender(calender)
                .name(scheduleName)
                .date(scheduleDate.toString())
                .time(scheduleTime.toString())
                .calenderDate(calenderDate.toString())
                .achievement(0L) // 초기 성취도 0
                .build();

    }
}
