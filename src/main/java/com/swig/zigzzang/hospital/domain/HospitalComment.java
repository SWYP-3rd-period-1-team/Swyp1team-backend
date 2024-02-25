package com.swig.zigzzang.hospital.domain;


import com.swig.zigzzang.member.domain.Member;
import com.swig.zigzzang.utill.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "hospital_comment")
@Getter
public class HospitalComment extends BaseEntity { // 병원 댓글 Entity

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hospital_comment_id")
    private Long hospitalCommentId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @Column
    private String content; // 댓글 내용

    @Column
    private Long reportCount; // 신고수

    @Column
    private Boolean isDeleted; // 삭제 여부

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "parent_comment_id")
    private Set<HospitalChildComment> childCommentList = new HashSet<>(); // 댓글과 관련된 대댓글 목록

    @Builder
    public HospitalComment(Member member, Hospital hospital, String content) {
        this.member = member;
        this.hospital = hospital;
        this.content = content;
        this.reportCount = 0L; // 초기값 0
        this.isDeleted = false;// 초기값 false
    }
}
