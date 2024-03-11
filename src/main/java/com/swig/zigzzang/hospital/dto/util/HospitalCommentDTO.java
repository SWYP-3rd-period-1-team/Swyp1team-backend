package com.swig.zigzzang.hospital.dto.util;

import com.swig.zigzzang.hospital.domain.HospitalComment;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class HospitalCommentDTO {

    private Long hospitalCommentId; // 병원 댓글 ID

    private Long memberId; // 작성 회원 번호

    private String imageUrl; // 프로필 이미지
    
    private String nickName; // 회원 닉네임

    private String content; // 댓글 내용

    private Long reportCount; // 신고수

    private Long star; // 별점

    private LocalDate lastModifyDate; // 최근 수정 날짜

    private List<HospitalCommentDTO> children;


    public static HospitalCommentDTO of(HospitalComment hospitalComment) {
       List<HospitalCommentDTO> children = new ArrayList<>();
       return hospitalComment.getIsDeleted() ?
                HospitalCommentDTO.builder()
                        .hospitalCommentId(hospitalComment.getHospitalCommentId())
                        .memberId(hospitalComment.getMember().getMemberId())
                        .imageUrl(hospitalComment.getMember().getProfileimage())
                        .nickName(hospitalComment.getMember().getNickname())
                        .content("삭제된 댓글입니다.")
                        .reportCount(hospitalComment.getReportCount())
                        .star(hospitalComment.getStar())
                        .lastModifyDate(LocalDate.from(hospitalComment.getModifyDate()))
                        .children(children)
                        .build() :

                HospitalCommentDTO.builder()
                        .hospitalCommentId(hospitalComment.getHospitalCommentId())
                        .memberId(hospitalComment.getMember().getMemberId())
                        .imageUrl(hospitalComment.getMember().getProfileimage())
                        .nickName(hospitalComment.getMember().getNickname())
                        .content(hospitalComment.getContent())
                        .reportCount(hospitalComment.getReportCount())
                        .star(hospitalComment.getStar())
                        .lastModifyDate(LocalDate.from(hospitalComment.getModifyDate()))
                        .children(children)
                        .build();

    }





}
