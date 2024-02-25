package com.swig.zigzzang.hospital.domain;


import com.swig.zigzzang.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member_hospital")
@Getter
public class MemberHospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_hospital_id")
    private Long memberHospitalId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @Column
    private Long star; // 별점

    @Column
    private Boolean bookmark; // 찜 여부

    @Builder
    public MemberHospital(Member member, Hospital hospital, Long star) {
        this.member = member;
        this.hospital = hospital;
        this.star = star;
        this.bookmark = false;
    }
}
