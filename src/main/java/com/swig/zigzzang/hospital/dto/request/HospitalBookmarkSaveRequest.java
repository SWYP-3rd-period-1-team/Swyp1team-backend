package com.swig.zigzzang.hospital.dto.request;

import com.swig.zigzzang.hospital.domain.Hospital;
import com.swig.zigzzang.hospital.domain.MemberHospital;
import com.swig.zigzzang.member.domain.Member;
import com.swig.zigzzang.member.exception.MemberNotExistException;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

import java.util.Optional;

@Data
public class HospitalBookmarkSaveRequest {

    @NotNull(message = "유저ID가 필요합니다.")
    @Schema(description = "로그인 회원 userId", nullable = false, example = "test")
    private String userId;


    @NotNull(message = "지도 ID 파라미터가 필요합니다.")
    @Schema(description = "병원 구글맵 ID", nullable = false, example = "ChIJgUbEo8cfqokR5lP9_Wh_DaM")
    private String googleMapId;

    @NotNull(message = "북마크 여부 파라미터가 필요합니다.")
    @Schema(description = "북마크 여부", nullable = false, example = "true")
    private Boolean bookmark;


    public Hospital toHospitalEntity() {
        return Hospital.builder()
                .googleMapId(googleMapId)
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




