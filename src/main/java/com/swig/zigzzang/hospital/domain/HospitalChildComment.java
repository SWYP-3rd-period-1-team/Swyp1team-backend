package com.swig.zigzzang.hospital.domain;


import com.swig.zigzzang.member.domain.Member;
import com.swig.zigzzang.utill.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "hospital_child_comment")
@Getter
public class HospitalChildComment extends BaseEntity { // 병원 대댓글 Entity

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "child_comment_id")
    private Long childCommentId;

    @ManyToOne
    @JoinColumn(referencedColumnName = "member_id",nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "tag_member_id")
    private Member tagMember; // 상위 댓글 멤버 정보 참조

    @Column(name = "parent_comment_id")
    private Long parentCommentId;

    private String content;

    @Builder
    public HospitalChildComment(Member member, Member tagMember, Long parentCommentId, String content) {
        this.member = member;
        this.tagMember = tagMember;
        this.parentCommentId = parentCommentId;
        this.content = content;
    }
}
