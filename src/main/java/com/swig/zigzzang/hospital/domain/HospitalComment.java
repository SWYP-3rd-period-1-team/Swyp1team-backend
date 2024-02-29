package com.swig.zigzzang.hospital.domain;


import com.swig.zigzzang.member.domain.Member;
import com.swig.zigzzang.utill.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "hospital_comment")
@Getter
public class HospitalComment extends BaseEntity { // 병원 댓글 Entity

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hospital_comment_id")
    private Long hospitalCommentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private HospitalComment parent;

    @Column
    private String content; // 댓글 내용

    @Column
    private Long reportCount; // 신고수

    @Column
    private Boolean isDeleted; // 삭제 여부

    @Column
    private Long star;// 병원 별점


    @Builder.Default
    @OneToMany(mappedBy = "parent",orphanRemoval = true)
    private List<HospitalComment> childCommentList = new ArrayList<>();


}
