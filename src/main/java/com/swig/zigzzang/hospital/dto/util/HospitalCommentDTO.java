package com.swig.zigzzang.hospital.dto.util;

import com.swig.zigzzang.hospital.domain.HospitalComment;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class HospitalCommentDTO {

    private Long hospitalCommentId; // 병원 댓글 ID

    private Long memberId; // 작성 회원번호

    private String content; // 댓글 내용

    private Long reportCount; // 신고수

    private Long star; // 별점

    private List<HospitalCommentDTO> children = new ArrayList<>();


    public static HospitalCommentDTO of(HospitalComment hospitalComment) {
       List<HospitalCommentDTO> children = new ArrayList<>();
       return hospitalComment.getIsDeleted() ?
                HospitalCommentDTO.builder()
                        .hospitalCommentId(hospitalComment.getHospitalCommentId())
                        .memberId(hospitalComment.getMember().getMemberId())
                        .content("삭제된 댓글입니다.")
                        .reportCount(hospitalComment.getReportCount())
                        .star(hospitalComment.getStar())
                        .children(children)
                        .build() :

                HospitalCommentDTO.builder()
                        .hospitalCommentId(hospitalComment.getHospitalCommentId())
                        .memberId(hospitalComment.getMember().getMemberId())
                        .content(hospitalComment.getContent())
                        .reportCount(hospitalComment.getReportCount())
                        .star(hospitalComment.getStar())
                        .children(children)
                        .build();

    }





}
