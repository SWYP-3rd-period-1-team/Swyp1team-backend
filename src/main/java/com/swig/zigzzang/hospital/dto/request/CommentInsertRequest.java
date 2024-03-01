package com.swig.zigzzang.hospital.dto.request;


import com.swig.zigzzang.hospital.domain.Hospital;
import com.swig.zigzzang.hospital.domain.HospitalComment;
import com.swig.zigzzang.member.domain.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentInsertRequest {

    @NotNull(message = "댓글을 입력해 주세요")
    @Schema(description = "댓글 내용", nullable = false, example = "댓글 내용")
    private String content;

    @NotNull(message = "별점을 입력해 주세요")
    @Schema(description = "별점", nullable = false, example = "1")
    private Long star;


    public HospitalComment toEntity(Member member, Hospital hospital){ // 리뷰 dto->entity
        return HospitalComment.builder()
                .member(member)
                .hospital(hospital)
                .parent(null)
                .content(content)
                .reportCount(0L)
                .isDeleted(false)
                .star(star)
                .build();
    }
}
