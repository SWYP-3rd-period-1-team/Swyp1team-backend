package com.swig.zigzzang.hospital.dto;

import com.swig.zigzzang.hospital.domain.Hospital;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;


public record HospitalSaveRequest(

        @NotNull(message = "병원의 구글맵 ID를 입력해주세요")
        @Schema(description = "병원 구글맵 ID", nullable = false, example = "111111111")
        Long kakaoMapId
) {
    
    public Hospital toEntity() {
        return Hospital.builder()
                .googleMapId(kakaoMapId)
                .build();

    }
}
