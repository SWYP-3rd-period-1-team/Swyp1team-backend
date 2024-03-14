package com.swig.zigzzang.calender.dto.request.Supplement;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record SupplementAchieveUpdateRequest(

        @Schema(description = "달성도 boolean 가변 배열", nullable = false, example = "[true,false,true,false]")
        Boolean[] achieveArray // 달성 여부 배열
) {


}
