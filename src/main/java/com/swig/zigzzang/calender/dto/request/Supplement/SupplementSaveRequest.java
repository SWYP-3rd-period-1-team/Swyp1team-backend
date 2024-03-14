package com.swig.zigzzang.calender.dto.request.Supplement;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.swig.zigzzang.calender.domain.Calender;
import com.swig.zigzzang.calender.domain.Supplement;
import com.swig.zigzzang.member.domain.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
public record SupplementSaveRequest(

        @NotNull(message = "영양제 이름을 입력해 주세요.")
        @Schema(description = "영양제 이름", nullable = false, example = "00 영양제")
        String supplementName, // 영양제 이름

        @NotNull(message = "1회 섭취량을 입력해 주세요")
        @Schema(description = "영양제 1회 섭취량", nullable = false, example = "1")
        Long supplementNumber,// 영양제 1회 섭취량

        @NotNull(message = "영양제 하루 섭취 횟수를 입력해 주세요")
        @Schema(description = "영양제 하루 섭취 횟수", nullable = false, example = "3")
        Long supplementFrequency, // 영양제 1일 섭취 횟수

        @NotNull(message = "캘린더 날짜를 입력해 주세요")
        @Schema(description = "캘린더 날짜", nullable = false, example = "2024-03-10")
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate calenderDate // 캘린더 날짜
) {

    public Supplement toEntity(Member member, Calender calender) {
        return Supplement.builder()
                .name(supplementName)
                .calender(calender)
                .number(supplementNumber)
                .frequency(supplementFrequency)
                .calenderDate(calenderDate.toString())
                .achieveArray(null)
                .build();

    }
}
