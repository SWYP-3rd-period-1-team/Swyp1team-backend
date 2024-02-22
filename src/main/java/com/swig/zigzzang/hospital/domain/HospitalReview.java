package com.swig.zigzzang.hospital.domain;

import com.swig.zigzzang.member.domain.Member;
import com.swig.zigzzang.utill.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "hospital_review")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class HospitalReview extends BaseEntity { // 병원리뷰 Entity

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hospital_review_id")
    private Long hospitalReviewId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @Column
    private String content;  // 리뷰 내용

    @Column
    private Boolean isDeleted; // 삭제 여부

    @Column
    private Boolean bookmark; //  북마크 등록 여부

    @Column
    private Long star; // 별점

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "parent_review_id")
    private Set<HospitalChildReview> childReviews = new HashSet<>();


    public HospitalReview(Member member,Long hospitalId,String content){

    }

}
