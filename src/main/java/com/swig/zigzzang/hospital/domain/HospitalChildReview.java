package com.swig.zigzzang.hospital.domain;

import com.swig.zigzzang.member.domain.Member;
import com.swig.zigzzang.utill.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "hospital_review")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class HospitalChildReview extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hospital_child_review_id")
    private Long hospitalChildReviewId;

    @ManyToOne
    @JoinColumn(referencedColumnName = "member_id",nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "tag_member_id")
    private Member tagMember; // 상위 리뷰의 Member 정보

    @Column(name = "parent_review_id")
    private Long parentReviewId; // 상위 리뷰 ID

    @Column
    private String content; // 리뷰의 댓글 내용

    @Column
    private String star; // 평점



}
