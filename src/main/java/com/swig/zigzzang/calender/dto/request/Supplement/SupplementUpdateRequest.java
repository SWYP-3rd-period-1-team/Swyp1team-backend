package com.swig.zigzzang.calender.dto.request.Supplement;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record SupplementUpdateRequest(

        @NotNull(message = "영양제 이름을 입력해 주세요.")
        @Schema(description = "영양제 이름", nullable = false, example = "00 영양제")
        String supplementName, // 영양제 이름

        @NotNull(message = "1회 섭취량을 입력해 주세요")
        @Schema(description = "영양제 1회 섭취량", nullable = false, example = "1")
        Long supplementNumber,// 영양제 1회 섭취량

        @NotNull(message = "영양제 하루 섭취 횟수를 입력해 주세요")
        @Schema(description = "영양제 하루 섭취 횟수", nullable = false, example = "3")
        Long supplementFrequency // 영양제 1일 섭취 횟수
) {

}
