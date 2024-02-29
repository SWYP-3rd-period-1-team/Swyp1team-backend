package com.swig.zigzzang.hospital.dto.request;


import com.swig.zigzzang.hospital.domain.Hospital;
import com.swig.zigzzang.hospital.domain.HospitalComment;
import com.swig.zigzzang.member.domain.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentUpdateRequest {

    @NotNull(message = "유저 ID가 필요합니다.")
    @Schema(description = "로그인 멤버 userId", nullable = false, example = "test1234")
    private String userId;

    @NotNull(message = "지도 ID 파라미터가 필요합니다.")
    @Schema(description = "병원 구글맵 ID", nullable = false, example = "ChIJgUbEo8cfqokR5lP9_Wh_DaM")
    private String googleMapId;

    @NotNull(message = "댓글을 입력해 주세요")
    @Schema(description = "댓글 내용", nullable = false, example = "댓글 내용")
    private String content;

    @NotNull(message = "별점을 입력해 주세요")
    @Schema(description = "별점", nullable = false, example = "1")
    private Long star;


    public HospitalComment toEntity(Member member, Hospital hospital){
        return HospitalComment.builder()
                .member(member)
                .hospital(hospital)
                .content(content)
                .reportCount(0L)
                .isDeleted(false)
                .star(star)
                .build();
    }
}
