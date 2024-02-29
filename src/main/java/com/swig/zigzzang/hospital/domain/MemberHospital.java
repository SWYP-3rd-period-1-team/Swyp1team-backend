package com.swig.zigzzang.hospital.domain;


import com.swig.zigzzang.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member_hospital")
@Builder
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
    private Boolean bookmark; // 찜 여부


    // 북마크 여부 setting
    public void setBookmark(Boolean bookmark) {
        this.bookmark = bookmark;
    }
}
