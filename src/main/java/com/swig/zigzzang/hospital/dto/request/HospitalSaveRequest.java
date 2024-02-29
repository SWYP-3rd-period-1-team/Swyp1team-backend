package com.swig.zigzzang.hospital.dto.request;

import com.swig.zigzzang.hospital.domain.Hospital;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class HospitalSaveRequest {

    @NotNull(message = "지도 ID 파라미터가 필요합니다.")
    @Schema(description = "병원 구글맵 ID", nullable = false, example = "ChIJgUbEo8cfqokR5lP9_Wh_DaM")
    private String googleMapId;



    public Hospital toEntity() {
        return Hospital.builder()
                .googleMapId(googleMapId)
                .build();

    }

}




