package com.swig.zigzzang.hospital.dto.response;

import lombok.Builder;

@Builder
public record HospitalSaveResponse(
        String message
) {
    public static HospitalSaveResponse of() {
        return HospitalSaveResponse.builder()
                .message("병원 등록 처리 성공")
                .build();

    }
}