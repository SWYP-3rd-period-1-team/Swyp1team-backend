package com.swig.zigzzang.hospital.dto.request;

import com.swig.zigzzang.hospital.domain.Hospital;
import com.swig.zigzzang.hospital.domain.MemberHospital;
import com.swig.zigzzang.member.domain.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

import java.util.Optional;

@Data
public class HospitalBookmarkSaveRequest {

    @NotNull(message = "북마크 여부 파라미터가 필요합니다.")
    @Schema(description = "북마크 여부", nullable = false, example = "true")
    private Boolean bookmark;


    public Hospital toHospitalEntity(String hospitalId) {
        return Hospital.builder()
                .googleMapId(hospitalId)
                .build();

    }

    public MemberHospital toMemberHospitalEntity(Member member,Hospital hospital){
        return MemberHospital.builder()
                .member(member)
                .bookmark(bookmark)
                .hospital(hospital)
                .build();

    }
}




